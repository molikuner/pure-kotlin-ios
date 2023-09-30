package com.molikuner.kn.compose.uikit

import platform.posix.CLOCK_MONOTONIC_RAW
import platform.posix.clock_gettime_nsec_np

internal object NativeTime {
    private val start: ULong = monotonicNanoTime()
    private fun monotonicNanoTime(): ULong = clock_gettime_nsec_np(CLOCK_MONOTONIC_RAW.toUInt())
    fun runtimeNanos(): Long = (monotonicNanoTime() - start).toLong()
}
