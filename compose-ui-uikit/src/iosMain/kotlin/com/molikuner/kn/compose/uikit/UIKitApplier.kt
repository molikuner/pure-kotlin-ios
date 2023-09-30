package com.molikuner.kn.compose.uikit

import androidx.compose.runtime.AbstractApplier
import platform.UIKit.UIStackView
import platform.UIKit.UIView

class UIKitApplier(root: UIView) : AbstractApplier<UIView>(root) {
    override fun insertTopDown(index: Int, instance: UIView) {
        println("insertTopDown(index = $index, instance = $instance)")

        val subView: UIView = instance

        subView.translatesAutoresizingMaskIntoConstraints = false

        val current = this.current
        if (current is UIStackView) {
            current.insertArrangedSubview(subView, index.toULong())
        } else {
            current.insertSubview(subView, index.toLong())
        }
    }

    override fun insertBottomUp(index: Int, instance: UIView) = Unit

    override fun remove(index: Int, count: Int) {
        println("remove(index = $index, count = $count)")

        val current = current
        val subviews = if (current is UIStackView) {
            current.arrangedSubviews
        } else {
            current.subviews
        }
        subviews.subList(index, index + count).forEach {
            val view = it as UIView
            println("i got $it to remove")
            view.removeFromSuperview()
        }
    }

    override fun move(from: Int, to: Int, count: Int) {
        println("move(from = $from, to = $to, count = $count)")
    }

    override fun onClear() {
        println("onClear()")
        root.subviews.forEach { (it as UIView).removeFromSuperview() }
    }
}

