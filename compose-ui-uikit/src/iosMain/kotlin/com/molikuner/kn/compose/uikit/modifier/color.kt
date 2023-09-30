package com.molikuner.kn.compose.uikit.modifier

import platform.CoreGraphics.CGFloat
import platform.UIKit.UIColor

fun Modifier.background(color: UIColor) = then("background[$color]") { view ->
    view.backgroundColor = color
}

fun Modifier.alpha(alpha: CGFloat) = then("alpha[$alpha]") { view ->
    view.alpha = alpha
}
