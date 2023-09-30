package com.molikuner.kn.ios

import com.molikuner.kn.compose.uikit.ComposeUIViewController
import com.molikuner.kn.compose.uikit.modifier.Modifier
import com.molikuner.kn.compose.uikit.modifier.fillMaxSize
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationDelegateProtocol
import platform.UIKit.UIApplicationDelegateProtocolMeta
import platform.UIKit.UIResponder
import platform.UIKit.UIResponderMeta
import platform.UIKit.UIWindow

class AppDelegate @OverrideInit constructor() : UIResponder(), UIApplicationDelegateProtocol {
    companion object : UIResponderMeta(), UIApplicationDelegateProtocolMeta

    private val uiWindow: UIWindow by lazy {
        UIWindow().apply {
            rootViewController = ComposeUIViewController { App(Modifier.fillMaxSize()) }
        }
    }

    override fun application(application: UIApplication, didFinishLaunchingWithOptions: Map<Any?, *>?): Boolean {
        uiWindow.makeKeyAndVisible()

        return true
    }
}
