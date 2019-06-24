package main

/*import platform.Foundation.NSData
import platform.UIKit.UIImage
import platform.UIKit.UIView
import platform.darwin.**/
import kotlinx.coroutines.Dispatchers

actual fun getPlatformName() = "iOS"

internal actual val ApplicationDispatcher = Dispatchers.Default
 /*   NsQueueDispatcher(dispatch_get_main_queue())

internal class NsQueueDispatcher(
    private val dispatchQueue: dispatch_queue_t
) : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatchQueue) {
            block.run()
        }
    }
}*/