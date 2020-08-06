package com.smallsoho.mcplugin.image

import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.api.BaseVariantImpl
import com.smallsoho.mcplugin.image.`interface`.IBigImage
import com.smallsoho.mcplugin.image.utils.*
import com.smallsoho.mcplugin.image.webp.WebpUtils
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.TaskInputs
import java.io.File
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future


class ImagePlugin : Plugin<Project> {

    var prefix = "grass-------------"
    private lateinit var mcImageProject: Project
    private lateinit var mcImageConfig: Config
    private var oldSize: Long = 0
    private var newSize: Long = 0
    val bigImgList = ArrayList<String>()

    var isDebugTask = false
    var isContainAssembleTask = false
    var isAppModule = false

    override fun apply(project: Project) {

        mcImageProject = project
        prefix = prefix + project.name+" : "
        TaskLogger.log("", proj = project)
        //check is library or application
        val hasAppPlugin = project.plugins.hasPlugin("com.android.application")
        isAppModule = hasAppPlugin
        val variants = if (hasAppPlugin) {
            (project.property("android") as AppExtension).applicationVariants
        } else {
            (project.property("android") as LibraryExtension).libraryVariants
        }

        LogUtil.log("${prefix} apply() hasAppPlugin: $hasAppPlugin")
        //set config
        project.extensions.create("McImageConfig", Config::class.java)
        mcImageConfig = project.property("McImageConfig") as Config

        checkMcTools(project)
        if (Tools.isLinux()) {
            Tools.chmod()
        }

        LogUtil.log("McImageConfig: \n $mcImageConfig")
        project.gradle.taskGraph.whenReady {
            it.allTasks.forEach { task ->
                val taskName = task.name
                if (taskName.contains("assemble") || taskName.contains("resguard") || taskName.contains(
                        "bundle"
                    )
                ) {
                    if (taskName.toLowerCase().endsWith("debug") &&
                        taskName.toLowerCase().contains("debug")
                    ) {
                        isDebugTask = true
                    }
                    isContainAssembleTask = true
                    return@forEach
                }
            }
        }

        project.afterEvaluate {
            LogUtil.log("${prefix}afterEvaluate is working rootDir: "+project.rootDir)
//            TaskLogger.logAllTasks(project)
            variants.all { variant ->
                variant as BaseVariantImpl
                LogUtil.log("${prefix}afterEvaluate variant: ${variant.name}")
//                TaskLogger.logInputs(project.name, proj = project)
                TaskLogger.logOutputs(project.name, proj = project)
                if (isAppModule) {
                    val mergeResourcesTask = variant.mergeResourcesProvider.get()
                    val mcPicTask = createPicTask(project, variant)
                    //inject task
                    (project.tasks.findByName(mcPicTask.name) as Task).dependsOn(
//                        mergeResourcesTask.taskDependencies.getDependencies(
//                            mergeResourcesTask
//                        )
                    )
                    mergeResourcesTask.dependsOn(project.tasks.findByName(mcPicTask.name))
                }

                if (!isAppModule) {
                    var compileTask = project.tasks.findByName("compileDebugLibraryResources")
                    LogUtil.log("$prefix compileTask: $compileTask")
                    compileTask?.let {
                        TaskLogger.logCompileResourceTask(compileTask)
                        var libraryPicTask =
                            createLibraryPicTask(project, variant, compileTask.inputs)
//                        (libraryPicTask).dependsOn(
//                            compileTask.taskDependencies.getDependencies(compileTask)
//                        )
                        compileTask.dependsOn(libraryPicTask)
                    }
                }
            }
        }

    }

    private fun createPicTask(project: Project, variant: BaseVariantImpl): Task {
        val mcPicTask = project.task("McImage${variant.name.capitalize()}")

        mcPicTask.doLast {
            LogUtil.log(" ${project.name}---- mcPicTask.doLast")
            TaskLogger.logAllProjects(project)
            //debug enable
            if (isDebugTask && !mcImageConfig.enableWhenDebug) {
                LogUtil.log("Debug not run ^_^")
                return@doLast
            }

            //assemble passed
            if (!isContainAssembleTask) {
                LogUtil.log("Don't contain assemble task, mcimage passed")
                return@doLast
            }

            LogUtil.log("---- McImage Plugin Start ----")
            LogUtil.log(mcImageConfig.toString())

            val dir = variant.allRawAndroidResources.files.filter { file ->
               !file.path.endsWith("/build/intermediates/packaged_res/debug")
            }.toList()

            LogUtil.log("${prefix} mcPicTask.doLast: resource files------\n" + dir)
            val cacheList = ArrayList<String>()

            val imageFileList = ArrayList<File>()

            for (channelDir: File in dir) {
                traverseResDir(channelDir, imageFileList, cacheList, object : IBigImage {
                    override fun onBigImage(file: File) {
                        bigImgList.add(file.absolutePath)
                    }
                })
            }

            checkBigImage()

            val start = System.currentTimeMillis()

            mtDispatchOptimizeTask(imageFileList)
            LogUtil.log(sizeInfo())
            LogUtil.log("---- McImage Plugin End ----, Total Time(ms) : ${System.currentTimeMillis() - start}")
        }
        return mcPicTask
    }

    fun createLibraryPicTask(project: Project, variant: BaseVariantImpl,inputs: TaskInputs):Task {
        val libPicTask = project.task("McImageLibrary${variant.name.capitalize()}")
        if (inputs == null) {
            return libPicTask
        }
        libPicTask.doLast {
            LogUtil.log(" ${project.name}----libPicTask.doLast")
            //debug enable
            if (isDebugTask && !mcImageConfig.enableWhenDebug) {
                LogUtil.log("Debug not run ^_^")
                return@doLast
            }

            //assemble passed
            if (!isContainAssembleTask) {
                LogUtil.log("Don't contain assemble task, mcimage passed")
                return@doLast
            }

            val dir = inputs.files
            LogUtil.log("${prefix} libPicTask.doLast: " + dir)
            val cacheList = ArrayList<String>()

            val imageFileList = ArrayList<File>()

            for (channelDir: File in dir) {
                traverseResDir(channelDir, imageFileList, cacheList, object : IBigImage {
                    override fun onBigImage(file: File) {
                        bigImgList.add(file.absolutePath)
                    }
                })
            }

            checkBigImage()

            val start = System.currentTimeMillis()

            mtDispatchOptimizeTask(imageFileList)
            LogUtil.log(sizeInfo())
            LogUtil.log("---- McImage Plugin End ----, Total Time(ms) : ${System.currentTimeMillis() - start}")
        }
        return libPicTask
    }

    private fun traverseResDir(
        file: File,
        imageFileList: ArrayList<File>,
        cacheList: ArrayList<String>,
        iBigImage: IBigImage
    ) {
        if (cacheList.contains(file.absolutePath)) {
            return
        } else {
            cacheList.add(file.absolutePath)
        }
        if (file.isDirectory) {
            file.listFiles()?.forEach {
                if (it.isDirectory) {
                    traverseResDir(it, imageFileList, cacheList, iBigImage)
                } else {
                    filterImage(it, imageFileList, iBigImage)
                }
            }
        } else {
            filterImage(file, imageFileList, iBigImage)
        }
    }

    private fun filterImage(file: File, imageFileList: ArrayList<File>, iBigImage: IBigImage) {
        if (mcImageConfig.whiteList.contains(file.name) || !ImageUtil.isImage(file)) {
            return
        }
        if (((mcImageConfig.isCheckSize && ImageUtil.isBigSizeImage(file, mcImageConfig.maxSize))
                    || (mcImageConfig.isCheckPixels
                    && ImageUtil.isBigPixelImage(
                file,
                mcImageConfig.maxWidth,
                mcImageConfig.maxHeight
            )))
            && !mcImageConfig.bigImageWhiteList.contains(file.name)
        ) {
            iBigImage.onBigImage(file)
        }
        imageFileList.add(file)
    }

    private fun mtDispatchOptimizeTask(imageFileList: ArrayList<File>) {
        LogUtil.log("${prefix}mtDispatchOptimizeTask: " + imageFileList.size)
        if (imageFileList.size == 0 || bigImgList.isNotEmpty()) {
            return
        }
        val coreNum = Runtime.getRuntime().availableProcessors()
        if (imageFileList.size < coreNum || !mcImageConfig.multiThread) {
            for (file in imageFileList) {
                optimizeImage(file)
            }
        } else {
            val results = ArrayList<Future<Unit>>()
            val pool = Executors.newFixedThreadPool(coreNum)
            val part = imageFileList.size / coreNum
            for (i in 0 until coreNum) {
                val from = i * part
                val to = if (i == coreNum - 1) imageFileList.size - 1 else (i + 1) * part - 1
                results.add(pool.submit(Callable<Unit> {
                    for (index in from..to) {
                        optimizeImage(imageFileList[index])
                    }
                }))
            }
            for (f in results) {
                try {
                    f.get()
                } catch (ignore: Exception) {
                }
            }
        }
    }

    private fun optimizeImage(file: File) {
        val path: String = file.path
        LogUtil.log("${mcImageProject.name} ${prefix}optimizeImage: " + path)
        if (File(path).exists()) {
            oldSize += File(path).length()
        }
        when (mcImageConfig.optimizeType) {
            Config.OPTIMIZE_WEBP_CONVERT ->
                WebpUtils.securityFormatWebp(file, mcImageConfig, mcImageProject)
            Config.OPTIMIZE_COMPRESS_PICTURE ->
                CompressUtil.compressImg(file)
        }
        countNewSize(path)
    }

    private fun countNewSize(path: String) {
        if (File(path).exists()) {
            newSize += File(path).length()
        } else {
            //转成了webp
            val indexOfDot = path.lastIndexOf(".")
            val webpPath = path.substring(0, indexOfDot) + ".webp"
            if (File(webpPath).exists()) {
                newSize += File(webpPath).length()
            } else {
                LogUtil.log("McImage: optimizeImage have some Exception!!!")
            }
        }
    }

    private fun checkBigImage() {
        if (bigImgList.size != 0) {
            val stringBuffer = StringBuffer(
                "You have big Imgages with big size or large pixels," +
                        "please confirm whether they are necessary or whether they can to be compressed. " +
                        "If so, you can config them into bigImageWhiteList to fix this Exception!!!\n"
            )
            for (i: Int in 0 until bigImgList.size) {
                stringBuffer.append(bigImgList[i])
                stringBuffer.append("\n")
            }
            throw GradleException(stringBuffer.toString())
        }
    }


    private fun checkMcTools(project: Project) {
        if (mcImageConfig.mctoolsDir.isBlank()) {
            FileUtil.setRootDir(project.rootDir.path)
        } else {
            FileUtil.setRootDir(mcImageConfig.mctoolsDir)
        }

        if (!FileUtil.getToolsDir().exists()) {
            throw GradleException("You need put the mctools dir in project root")
        }
    }

    private fun sizeInfo(): String {
        return "->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n" +
                "before McImage optimize: " + oldSize / 1024 + "KB\n" +
                "after McImage optimize: " + newSize / 1024 + "KB\n" +
                "McImage optimize size: " + (oldSize - newSize) / 1024 + "KB\n" +
                "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<-"


    }
}