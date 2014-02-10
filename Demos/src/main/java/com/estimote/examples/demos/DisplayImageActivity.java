package com.estimote.examples.demos;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class DisplayImageActivity extends Activity {

    public static final String TAG = "displayImage";

    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_layout);
        Bundle b = getIntent().getExtras();
        int major = b.getInt("major");
        new HttpRequestTask().execute(major);
    }

    private class HttpRequestTask extends AsyncTask<Integer, Void, BeaconImage> {
        @Override
        protected BeaconImage doInBackground(Integer... params) {
            try {
                URL url = new URL("http://warski.org/gandalf/" + params[0] + ".json");
                HttpGet httpRequest = new HttpGet(url.toURI());
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httpRequest);
                HttpEntity entity = response.getEntity();
                BufferedHttpEntity httpEntity = new BufferedHttpEntity(entity);
                String json = IOUtils.toString(httpEntity.getContent());
                return gson.fromJson(json, BeaconImage.class);
            } catch (URISyntaxException e) {
                Log.e(TAG, e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(BeaconImage beaconImage) {
            TextView userName = (TextView) findViewById(R.id.user_name);
            userName.setText(beaconImage.getName());
        }
    }
}
