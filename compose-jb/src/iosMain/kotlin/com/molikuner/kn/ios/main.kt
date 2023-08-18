package com.molikuner.kn.ios

import kotlinx.cinterop.cstr
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toCValues
import platform.Foundation.NSStringFromClass
import platform.UIKit.UIApplicationMain
import kotlin.system.exitProcess

fun main(vararg args: String): Unit = memScoped {
    val returnCode = UIApplicationMain(
        argc = args.size,
        argv = arrayOf("app", *args).map { it.cstr.ptr }.toCValues(),
        principalClassName = null,
        delegateClassName = NSStringFromClass(AppDelegate)
    )
    exitProcess(returnCode) // This can never happen, as UIApplicationMain does not return based on docs.
}

