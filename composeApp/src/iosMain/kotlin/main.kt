import androidx.compose.ui.window.ComposeUIViewController
import io.joseph.book.read.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
