package com.estimote.examples.demos;

import com.estimote.sdk.Beacon;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class BeaconImageProvider {

    private static RestTemplate restTemplate = new RestTemplate();

    static {
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public static String getBitmapForBeacon(Beacon beacon) {
        BeaconImage beaconImage = restTemplate.getForObject("", BeaconImage.class);
        return beaconImage.getUrl();
    }

}
