package com.grass.helloandroid.assets;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by grassswwang
 * on 2020/9/2
 * Email: grassswwang@tencent.com
 */
public class AssetsDemo {

    private static final String TAG = "AssetsDemo";

    public void test(Context context) {
        String assetsPath = new File("/sdcard/red_skin.skin").getAbsolutePath();
        addExtraAssets(context, assetsPath);
        listAssets(context);
        addAssetsKb(new File("/sdcard/assetstest.apk").getAbsolutePath());
        loadInnerSkin(new File("/sdcard/assetstest.apk").getAbsolutePath());
        loadInnerSkin(new File("/sdcard/red_skin.skin").getAbsolutePath());
    }

    private void listAssets(Context context) {
        try {
            String[] list = context.getAssets().list("");
            for (String s : list) {
                Log.i(TAG, "listAssets: "+s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listAssets(AssetManager manager) {
        try {
            String[] list = manager.list("");
            for (String s : list) {
                Log.i(TAG, "listAssets: "+s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addSkinPath(Context context, String skinPkgPath) throws Exception {
        PackageManager packageManager = context.getPackageManager();
        packageManager.getPackageArchiveInfo(skinPkgPath,
                PackageManager.GET_SIGNATURES | PackageManager.GET_META_DATA);

        AssetManager assetManager = AssetManager.class.newInstance();
        Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            addAssetPath.invoke(assetManager, context.getApplicationInfo().publicSourceDir);
            addAssetPath.invoke(assetManager, skinPkgPath);

        } else {
            //5.0以上，需要將assets 资源文件单独添加
            File assetsFile = new File(skinPkgPath);
            //  File assetsFile = Utils.generateIndependentAsssetsForl(new File((skinPkgPath)));
            addAssetPath.invoke(assetManager, skinPkgPath);
            addAssetPath.invoke(assetManager, context.getApplicationInfo().publicSourceDir);
            addAssetPath.invoke(assetManager, assetsFile.getAbsolutePath());
        }
    }

    public void addExtraAssets(Context context, String assetsPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.setAccessible(true);
            File assetsFile = new File(assetsPath);
            Object invoke = addAssetPath.invoke(assetManager, assetsPath);
            Object invoke1 = addAssetPath.invoke(assetManager, assetsFile.getAbsolutePath());
            Log.i(TAG, "addExtraAssets: invoke1: "+invoke1);
            String[] list = assetManager.list("");
            if (list != null) {
                Log.i(TAG, "addExtraAssets: list: "+list.length);
            }
            listAssets(assetManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addAssetsKb(String skinPkgPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.setAccessible(true);
            addAssetPath.invoke(assetManager, skinPkgPath);
            String[] list = assetManager.list("");
            if (list != null) {
                Log.i(TAG, "addExtraAssetsKb: list: "+list.length);
            }
            listAssets(assetManager);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private final static String ADD_ASSET_PATH = "addAssetPath";
    private String loadInnerSkin(String skinFile) {
        try {
            //加载该皮肤资源
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPathMethod = AssetManager.class.getMethod(ADD_ASSET_PATH, String.class);

            addAssetPathMethod.setAccessible(true);
            Object invoke = addAssetPathMethod.invoke(assetManager, skinFile);
            String assetsPath = "/sdcard/assetstest.apk";
            Object result2 = addAssetPathMethod.invoke(assetManager, new File(assetsPath).getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
