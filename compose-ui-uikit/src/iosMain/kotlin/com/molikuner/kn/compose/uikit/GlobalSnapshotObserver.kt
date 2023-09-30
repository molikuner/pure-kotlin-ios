package com.molikuner.kn.compose.uikit

import androidx.compose.runtime.snapshots.Snapshot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

internal object GlobalSnapshotObserver {
    private val observerScope = CoroutineScope(Dispatchers.Main)
    private val observerJob: Job

    init {
        val channel = Channel<Unit>(Channel.CONFLATED) // i.e. throw away requests while we're already updating
        // run compositions on Main thread
        observerJob = observerScope.launch {
            channel.consumeEach { Snapshot.sendApplyNotifications() }
        }
        Snapshot.registerGlobalWriteObserver {
            channel.trySend(Unit)
        }
    }

    fun ensureStarted() {
        check(observerJob.isActive) {
            "GlobalSnapshotObserver not started or failed... (check your classpath initialization logic)"
        }
    }
}
