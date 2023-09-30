package com.molikuner.kn.compose.uikit.modifier

import platform.UIKit.UIView

fun Modifier.width(value: Int): Modifier = then("width[$value]") { view ->
    view.addConstraint(view.widthAnchor.constraintEqualToConstant(value.toDouble()).apply { active = true })
}

fun Modifier.height(value: Int): Modifier = then("height[$value]") { view ->
    view.addConstraint(view.heightAnchor.constraintEqualToConstant(value.toDouble()).apply { active = true })
}

fun Modifier.fillMaxSize(): Modifier = then("fillMaxSize") { view ->
    val parentView: UIView = view.superview
        ?: throw IllegalStateException("modifier applied to UIView without parent - $view")

    parentView.addConstraints(
        listOf(
            view.leadingAnchor.constraintEqualToAnchor(parentView.leadingAnchor),
            view.trailingAnchor.constraintEqualToAnchor(parentView.trailingAnchor),
            view.topAnchor.constraintEqualToAnchor(parentView.topAnchor),
            view.bottomAnchor.constraintEqualToAnchor(parentView.bottomAnchor)
        ).onEach { it.active = true }
    )
}

fun Modifier.fillMaxWidth(): Modifier = then("fillMaxWidth") { view ->
    val parentView: UIView = view.superview
        ?: throw IllegalStateException("modifier applied to UIView without parent - $view")

    parentView.addConstraints(
        listOf(
            view.leadingAnchor.constraintEqualToAnchor(parentView.leadingAnchor),
            view.trailingAnchor.constraintEqualToAnchor(parentView.trailingAnchor),
        ).onEach { it.active = true }
    )
}

fun Modifier.fillMaxHeight(): Modifier = then("fillMaxHeight") { view ->
    val parentView: UIView = view.superview
        ?: throw IllegalStateException("modifier applied to UIView without parent - $view")

    parentView.addConstraints(
        listOf(
            view.topAnchor.constraintEqualToAnchor(parentView.leadingAnchor),
            view.bottomAnchor.constraintEqualToAnchor(parentView.trailingAnchor),
        ).onEach { it.active = true }
    )
}
