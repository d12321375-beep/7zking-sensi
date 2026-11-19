package com.zking.sensi

import android.app.Activity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast

class MainActivity : Activity() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webView = WebView(this)
        setContentView(webView)

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        webView.addJavascriptInterface(AppBridge(this), "AndroidApp")
        webView.loadUrl("file:///android_asset/index.html")
    }

    @Deprecated("Deprecated in Android, retained for back navigation")
    override fun onBackPressed() {
        if (::webView.isInitialized && webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    class AppBridge(private val activity: Activity) {
        @JavascriptInterface
        fun copiarTexto(texto: String) {
            val clipboard = activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(ClipData.newPlainText("⁷zking.sensi", texto))
            activity.runOnUiThread {
                Toast.makeText(activity, "Sensibilidade copiada!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
