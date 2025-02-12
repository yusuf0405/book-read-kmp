import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import io.joseph.book.read.App
import io.joseph.book.read.di.initKoin
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val body = document.body ?: return
    initKoin()
    ComposeViewport(body) {
        App()
    }
}
