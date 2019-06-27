package main

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class CoroutinePresenter(
    // TODO: Используйте Dispatchers.Main вместо этого, когда это будет поддерживаться на iOS
    private val mainContext: CoroutineContext,
    private val baseView: BaseView
): CoroutineScope, BasePresenter {

    private val job = Job()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        baseView.showError(throwable.message ?: "Unknown error")
    }

    override val coroutineContext: CoroutineContext
        get() = mainContext + job + exceptionHandler

    override fun detachView() {
        job.cancel()
    }
}