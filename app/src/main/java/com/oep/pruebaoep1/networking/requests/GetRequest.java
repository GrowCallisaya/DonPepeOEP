package com.oep.pruebaoep1.networking.requests;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;


public abstract class GetRequest extends AsyncTask<String, Void, String> {
    private String TAG = GetRequest.class.getSimpleName();
    private Hashtable<String, String> headers;
    private String urlEndpoint;
    private int statusCode = 0;
    private String token = null;
    int TIMEOUT_VALUE = 2500;


    public GetRequest(String urlEndpoint) {
        this.headers = new Hashtable<String, String>();
        this.urlEndpoint = urlEndpoint;
    }

    @Override
    protected String doInBackground(String... urls) {
        HttpURLConnection urlConnection = null;

        try {
            urlConnection = (HttpURLConnection) new URL(urlEndpoint).openConnection();
            urlConnection.setReadTimeout(TIMEOUT_VALUE);
            urlConnection.setRequestMethod("GET");

            if (this.token != null)
                urlConnection.setRequestProperty("Authorization", "Token " + this.token);

            if(headers.size() > 0)
                for (String key: headers.keySet())
                    urlConnection.setRequestProperty(key, headers.get(key));

            urlConnection.setDoInput(true);
            urlConnection.connect();
            statusCode = urlConnection.getResponseCode();

            if (isSuccessStatusCode()) {
                return getStringFromStream(urlConnection.getInputStream());
            } else {
                return getStringFromStream(urlConnection.getErrorStream());
            }

        } catch (IOException e) {
            Log.e(TAG, "Exception --------->>>>: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return null;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void putHeader(String key, String value) {
        headers.put(key, value);
    }

    @Override
    protected void onPostExecute(String result) {

        if (result == null) onFailed("", 404);

        if (isSuccessStatusCode()) {
            this.onSucess(result, getStatusCode());
        } else {
            this.onFailed(result, getStatusCode());
        }

    }

    public abstract void onSucess(String result, int statusCode);

    public abstract void onFailed(String error, int statusCode);

    public String getStringFromStream(InputStream stream){
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return stringBuilder.toString();
    }
    public boolean isSuccessStatusCode(){
        return (getStatusCode() >= 200 && getStatusCode() <= 250);
    }
}
