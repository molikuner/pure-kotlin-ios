package com.molikuner.kn.compose.uikit.modifier

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import platform.UIKit.UIView


@Stable
interface Modifier {

    /**
     * Accumulates a value starting with [initial] and applying [operation] to the current value
     * and each element from outside in.
     *
     * Elements wrap one another in a chain from left to right; an [Element] that appears to the
     * left of another in a `+` expression or in [operation]'s parameter order affects all
     * of the elements that appear after it. [foldIn] may be used to accumulate a value starting
     * from the parent or head of the modifier chain to the final wrapped child.
     */
    fun <R> foldIn(initial: R, operation: (R, Element) -> R): R

    /**
     * Accumulates a value starting with [initial] and applying [operation] to the current value
     * and each element from inside out.
     *
     * Elements wrap one another in a chain from left to right; an [Element] that appears to the
     * left of another in a `+` expression or in [operation]'s parameter order affects all
     * of the elements that appear after it. [foldOut] may be used to accumulate a value starting
     * from the child or tail of the modifier chain up to the parent or head of the chain.
     */
    fun <R> foldOut(initial: R, operation: (Element, R) -> R): R

    /**
     * Returns `true` if [predicate] returns true for any [Element] in this [Modifier].
     */
    fun any(predicate: (Element) -> Boolean): Boolean

    /**
     * Returns `true` if [predicate] returns true for all [Element]s in this [Modifier] or if
     * this [Modifier] contains no [Element]s.
     */
    fun all(predicate: (Element) -> Boolean): Boolean

    /**
     * Concatenates this modifier with another.
     *
     * Returns a [Modifier] representing this modifier followed by [other] in sequence.
     */
    infix fun then(other: Modifier): Modifier =
        if (other === Modifier) this else CombinedModifier(this, other)

    /**
     * A single element contained within a [Modifier] chain.
     */
    interface Element : Modifier, (UIView) -> Unit {
        override fun <R> foldIn(initial: R, operation: (R, Element) -> R): R =
            operation(initial, this)

        override fun <R> foldOut(initial: R, operation: (Element, R) -> R): R =
            operation(this, initial)

        override fun any(predicate: (Element) -> Boolean): Boolean = predicate(this)

        override fun all(predicate: (Element) -> Boolean): Boolean = predicate(this)
    }

    companion object : Modifier {
        override fun <R> foldIn(initial: R, operation: (R, Element) -> R): R = initial
        override fun <R> foldOut(initial: R, operation: (Element, R) -> R): R = initial
        override fun any(predicate: (Element) -> Boolean): Boolean = false
        override fun all(predicate: (Element) -> Boolean): Boolean = true
        override infix fun then(other: Modifier): Modifier = other
        override fun toString() = "Modifier"
    }
}

/**
 * A node in a [Modifier] chain. A CombinedModifier always contains at least two elements;
 * a Modifier [outer] that wraps around the Modifier [inner].
 */
@Immutable
class CombinedModifier(
    internal val outer: Modifier,
    internal val inner: Modifier
) : Modifier {
    override fun <R> foldIn(initial: R, operation: (R, Modifier.Element) -> R): R =
        inner.foldIn(outer.foldIn(initial, operation), operation)

    override fun <R> foldOut(initial: R, operation: (Modifier.Element, R) -> R): R =
        outer.foldOut(inner.foldOut(initial, operation), operation)

    override fun any(predicate: (Modifier.Element) -> Boolean): Boolean =
        outer.any(predicate) || inner.any(predicate)

    override fun all(predicate: (Modifier.Element) -> Boolean): Boolean =
        outer.all(predicate) && inner.all(predicate)

    override fun equals(other: Any?): Boolean =
        other is CombinedModifier && outer == other.outer && inner == other.inner

    override fun hashCode(): Int = outer.hashCode() + 31 * inner.hashCode()

    override fun toString() = "[" + foldIn("") { acc, element ->
        if (acc.isEmpty()) element.toString() else "$acc, $element"
    } + "]"
}

internal inline fun Modifier.then(name: String, noinline element: (UIView) -> Unit): Modifier = this then object : Modifier.Element {
    override fun invoke(view: UIView) {
        view.apply(element)
    }

    override fun toString(): String {
        return name
    }
}

internal fun UIView.applyModifier(modifier: Modifier) {
    println("applying modifier to $this")
    modifier.foldIn(Unit) { _, element -> element(this)  }
}
