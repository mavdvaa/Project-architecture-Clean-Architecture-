package com.example.cleanarchitecture

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebScreen() {
    val context = LocalContext.current
    val webView = remember {
        WebView(context).apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl("https://www.google.com")
        }
    }

    // Обработка кнопки Back
    BackHandler(enabled = webView.canGoBack()) {
        webView.goBack()
    }

    AndroidView(factory = { webView }, modifier = Modifier.fillMaxSize())
}
