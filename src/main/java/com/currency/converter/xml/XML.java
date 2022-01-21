package com.currency.converter.xml;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Component
public class XML {
    public BufferedReader getXml(String url1) throws IOException {
        String url = url1;
        URLConnection urlConnection = null;
        try {
            urlConnection = new URL(url).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        urlConnection.setUseCaches(false);
        urlConnection.setDoOutput(true); // Triggers POST.
        urlConnection.setRequestProperty("content-type", "application/x-www-form-urlencoded");

        BufferedReader buf = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "windows-1251"));
        return buf;
    }
}
