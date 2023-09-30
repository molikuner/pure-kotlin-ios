package com.molikuner.kn.compose.uikit.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import com.molikuner.kn.compose.uikit.UIKitApplier
import com.molikuner.kn.compose.uikit.modifier.Modifier
import com.molikuner.kn.compose.uikit.modifier.applyModifier
import kotlinx.cinterop.readValue
import platform.CoreGraphics.CGRectZero
import platform.UIKit.UIAction
import platform.UIKit.UIButton
import platform.UIKit.UIControlEventTouchUpInside
import platform.UIKit.UIControlStateNormal

@Composable
fun UIButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) = ComposeNode<UIButton, UIKitApplier>(
    factory = {
        object : UIButton(frame = CGRectZero.readValue()) {
            override fun didMoveToSuperview() {
                println("UIButton.didMoveToSuperView")
                applyModifier(modifier)
            }
        }.apply {
            this.setTitle(title, UIControlStateNormal)
            this.addAction(UIAction.actionWithHandler { onClick() }, UIControlEventTouchUpInside)
        }
    },
    update = {
        update(title) { this.setTitle(it, UIControlStateNormal) }
        update(onClick) { this.addAction(UIAction.actionWithHandler { it() }, UIControlEventTouchUpInside) }
        update(modifier) { this.applyModifier(it) }
    }
)

