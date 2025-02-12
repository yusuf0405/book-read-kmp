package io.joseph.book.read

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.joseph.book.read.di.initKoin
import org.jetbrains.compose.ui.tooling.preview.Preview

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