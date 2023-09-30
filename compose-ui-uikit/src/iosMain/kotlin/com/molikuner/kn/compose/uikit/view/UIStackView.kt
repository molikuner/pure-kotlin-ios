package com.molikuner.kn.compose.uikit.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import com.molikuner.kn.compose.uikit.UIKitApplier
import com.molikuner.kn.compose.uikit.modifier.Modifier
import com.molikuner.kn.compose.uikit.modifier.applyModifier
import kotlinx.cinterop.readValue
import platform.CoreGraphics.CGRectZero
import platform.UIKit.UILayoutConstraintAxisVertical
import platform.UIKit.UIStackView
import platform.UIKit.UIStackViewAlignmentFill
import platform.UIKit.UIStackViewDistributionFillEqually

@Composable
fun UIStackView(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) = ComposeNode<UIStackView, UIKitApplier>(
    factory = {
        object : UIStackView(frame = CGRectZero.readValue()) {
            override fun didMoveToSuperview() {
                println("UIStackView.didMoveToSuperView")
                applyModifier(modifier)
            }
        }.apply {
            axis = UILayoutConstraintAxisVertical
            alignment = UIStackViewAlignmentFill
            distribution = UIStackViewDistributionFillEqually
        }
    },
    update = {
        update(modifier) { applyModifier(it) }
    },
    content = content
)
