package com.gradle.rescompressor.kotlinx

import java.io.PrintWriter
import java.io.StringWriter

val Throwable.stackTraceAsString: String
    get() = StringWriter().also {
        printStackTrace(PrintWriter(it))
    }.toString()
