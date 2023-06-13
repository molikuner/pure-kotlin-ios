package com.molikuner.kn.ios

import platform.UIKit.NSTextAlignmentCenter
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationDelegateProtocol
import platform.UIKit.UIApplicationDelegateProtocolMeta
import platform.UIKit.UIColor
import platform.UIKit.UILabel
import platform.UIKit.UIResponder
import platform.UIKit.UIResponderMeta
import platform.UIKit.UIViewController
import platform.UIKit.UIWindow

class AppDelegate @OverrideInit constructor() : UIResponder(), UIApplicationDelegateProtocol {
    companion object : UIResponderMeta(), UIApplicationDelegateProtocolMeta

    private val uiWindow: UIWindow by lazy {
        UIWindow().apply {
            rootViewController = UIViewController().apply {
                view = UILabel().apply {
                    text = "Hello from K/N!"
                    textAlignment = NSTextAlignmentCenter
                    backgroundColor = UIColor.greenColor
                }
            }
        }
    }

    override fun application(application: UIApplication, didFinishLaunchingWithOptions: Map<Any?, *>?): Boolean {
        uiWindow.makeKeyAndVisible()

        return true
    }
}
