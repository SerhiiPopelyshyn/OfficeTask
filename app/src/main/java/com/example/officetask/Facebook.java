package com.example.officetask;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class Facebook extends AppCompatActivity {
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facebook);

        webview=(WebView)findViewById(R.id.webView);
        webview.loadUrl("https://sites.google.com/view/fakefacebookserhii/головна");
    }

}
