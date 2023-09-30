package com.molikuner.kn.compose.uikit.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import com.molikuner.kn.compose.uikit.UIKitApplier
import com.molikuner.kn.compose.uikit.modifier.Modifier
import com.molikuner.kn.compose.uikit.modifier.applyModifier
import kotlinx.cinterop.readValue
import platform.CoreGraphics.CGRectZero
import platform.UIKit.NSLineBreakByTruncatingTail
import platform.UIKit.NSLineBreakMode
import platform.UIKit.NSTextAlignment
import platform.UIKit.NSTextAlignmentNatural
import platform.UIKit.UIColor
import platform.UIKit.UIFont
import platform.UIKit.UILabel

@Composable
fun UILabel(
    text: String,
    modifier: Modifier = Modifier,
    font: UIFont? = null,
    textColor: UIColor? = null,
    textAlignment: NSTextAlignment = NSTextAlignmentNatural,
    lineBreakMode: NSLineBreakMode = NSLineBreakByTruncatingTail,
    enabled: Boolean = true,
    numberOfLines: Long = 1
) = ComposeNode<UILabel, UIKitApplier>(
    factory = {
        println("factory of UILabel called")
        object : UILabel(frame = CGRectZero.readValue()) {
            override fun didMoveToSuperview() {
                println("UILabel.didMoveToSuperView")
                applyModifier(modifier)
            }
        }.apply {
            setText(text)
            setTextColor(textColor)
            setTextAlignment(textAlignment)
            setFont(font)
            setLineBreakMode(lineBreakMode)
            setEnabled(enabled)
            setNumberOfLines(numberOfLines)
        }
    },
    update = {
        update(text) { setText(it) }
        update(textColor) { setTextColor(it) }
        update(textAlignment) { setTextAlignment(it) }
        update(font) { setFont(it) }
        update(lineBreakMode) { setLineBreakMode(it) }
        update(enabled) { setEnabled(it) }
        update(numberOfLines) { setNumberOfLines(it) }
        update(modifier) { applyModifier(it) }
    }
)
