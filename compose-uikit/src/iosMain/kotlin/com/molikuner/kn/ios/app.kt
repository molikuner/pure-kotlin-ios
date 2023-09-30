package com.molikuner.kn.ios

import androidx.compose.runtime.Composable
import com.molikuner.kn.compose.uikit.modifier.Modifier
import com.molikuner.kn.compose.uikit.modifier.background
import com.molikuner.kn.compose.uikit.view.UILabel
import platform.UIKit.NSTextAlignmentCenter
import platform.UIKit.UIColor

@Composable
fun App(modifier: Modifier = Modifier) {
    UILabel(
        text = "Hello from Compose UIKit!",
        modifier = modifier
            .background(UIColor.greenColor),
        textAlignment = NSTextAlignmentCenter
    )
}
