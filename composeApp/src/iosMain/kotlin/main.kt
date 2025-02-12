import androidx.compose.ui.window.ComposeUIViewController
import io.joseph.book.read.App
import io.joseph.book.read.di.initKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController(
    configure = { initKoin() }
) {
    App()
}
