package com.dxtdkwt.zzh.commonlibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

public class BaseWebView extends WebView {
    public BaseWebView(Context context) {
        super(context);
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
