package com.example.dell.popularmovies.utils;

import android.net.Uri;

import com.example.dell.popularmovies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Scanner;


public class JsonUtils {
    final static String BASE_URL = "http://api.themoviedb.org/3/movie/popular?api_key=8b24d94146d6e8a8b7c99ad8fb9f4512";

    public static URL buildUrl() {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static Movie parseJson(String json){

        Movie res  = null;
        JSONObject data = null;

        try{
            data = new JSONObject(json);

            JSONArray results =data.getJSONArray("results");


        }catch (JSONException e) {
            e.printStackTrace();
        }
        return res;
    }

}
