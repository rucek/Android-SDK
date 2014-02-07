package com.estimote.examples.demos;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class DisplayImageActivity extends Activity {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Bundle b = getIntent().getExtras();
        int major = b.getInt("major");
        new HttpRequestTask().execute(major);
    }

    private class HttpRequestTask extends AsyncTask<Integer, Void, BeaconImage> {
        @Override
        protected BeaconImage doInBackground(Integer... params) {
            return restTemplate.getForObject("http://warski.org/gandalf/" + params[0] + ".json", BeaconImage.class);
        }

        @Override
        protected void onPostExecute(BeaconImage beaconImage) {
            WebView web = (WebView) findViewById(R.id.imageView);
            web.loadUrl(beaconImage.getUrl());
        }
    }
}
