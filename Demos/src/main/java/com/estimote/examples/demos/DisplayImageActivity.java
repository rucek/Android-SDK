package com.estimote.examples.demos;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class DisplayImageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        String url = b.getString("url");
        WebView web = (WebView) findViewById(R.id.imageView);
        web.loadUrl(url);
    }
}
