import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.joseph.book.read.App
import io.joseph.book.read.di.initKoin

fun main() = application {
    initKoin()
    Window(
        title = "book-read-kmp",
        onCloseRequest = ::exitApplication,
    ) {
        App()
    }
}

@Preview
@Composable
fun AppPreview() { App() }