package com.campanula.widget.webview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class ComWebView extends WebView {

    public ComWebView(Context context) {
        this(context,null);
    }

    public ComWebView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ComWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ComWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize();
    }
    private void initialize() {
        WebSettings webSettings = getSettings();
        webSettings.setJavaScriptEnabled(true);

    }
}
