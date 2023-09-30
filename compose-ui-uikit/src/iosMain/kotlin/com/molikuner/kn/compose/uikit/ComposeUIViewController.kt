package com.molikuner.kn.compose.uikit

import androidx.compose.runtime.BroadcastFrameClock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Composition
import androidx.compose.runtime.Recomposer
import com.molikuner.kn.compose.uikit.NativeTime.runtimeNanos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import platform.Foundation.NSCoder
import platform.UIKit.UIView
import platform.UIKit.UIViewController
import platform.UIKit.UIViewControllerMeta
import kotlin.time.Duration.Companion.milliseconds

fun ComposeUIViewController(content: @Composable () -> Unit): UIViewController = ComposeViewController().apply {
    setContent(content)
}

internal class ComposeViewController : UIViewController {
    companion object : UIViewControllerMeta()

    @OverrideInit
    constructor() : super(nibName = null, bundle = null)

    @OverrideInit
    constructor(coder: NSCoder) : super(coder)

    private val job = Job()
    private val dispatcher = Dispatchers.Unconfined
    private val frameClock = BroadcastFrameClock()
    private val coroutineScope = CoroutineScope(job + dispatcher + frameClock)

    private lateinit var recomposer: Recomposer
    private lateinit var composition: Composition

    private lateinit var content: @Composable () -> Unit

    override fun loadView() {
        println("loadView...")
        view = UIView()
    }

    override fun viewDidLoad() {
        println("view did load. starting compose runtime...")
        println("creating recomposer...")
        recomposer = Recomposer(coroutineScope.coroutineContext)

        println("creating composition...")
        composition = Composition(UIKitApplier(this.view), recomposer)

        println("starting recomposer...")
        coroutineScope.launch(start = CoroutineStart.UNDISPATCHED) {
            recomposer.runRecomposeAndApplyChanges()
        }

        println("starting 'frame clock'...")
        coroutineScope.launch(Dispatchers.Main) {
            while (isActive) {
                frameClock.sendFrame(runtimeNanos())
                delay(50.milliseconds)
            }
        }

        println("setting content...")
        composition.setContent(content)

        println("registering global snapshot write observer")
        GlobalSnapshotObserver.ensureStarted()
    }

    override fun finalize() {
        println("finalizing...")
        super.finalize()

        composition.dispose()
        recomposer.cancel()
        job.cancel()
    }

    fun setContent(content: @Composable () -> Unit) {
        this.content = content

        if (this::composition.isInitialized) {
            this.composition.setContent(content)
        }
    }
}
