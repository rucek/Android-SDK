package com.estimote.examples.demos;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;

import org.springframework.web.client.RestTemplate;

public class DisplayImageActivity extends Activity {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        String major = b.getString("major");
        new HttpRequestTask().execute(major);
    }

    private class HttpRequestTask extends AsyncTask<String, Void, BeaconImage> {
        @Override
        protected BeaconImage doInBackground(String... params) {
            return restTemplate.getForObject("http://warski.org/gandalf/" + params[0] + ".json", BeaconImage.class);
        }

        @Override
        protected void onPostExecute(BeaconImage beaconImage) {
            WebView web = (WebView) findViewById(R.id.imageView);
            web.loadUrl(beaconImage.getUrl());
        }
    }
}
