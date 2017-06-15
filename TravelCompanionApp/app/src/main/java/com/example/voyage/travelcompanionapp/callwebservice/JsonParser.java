package com.example.voyage.travelcompanionapp.callwebservice;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;


public class JsonParser extends AsyncTask<String, Integer, String> {

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	public InputStream istream;
	public String url;
	//HttpURLConnection connection = null;
	String content;

	protected String doInBackground(String... urls) {
		HttpURLConnection connection = null;

		URL url = null;
		try {
			url = new URL(urls[0]);

			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(false);
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			connection.connect();
			int responseCode = connection.getResponseCode();
			if (responseCode != HttpsURLConnection.HTTP_OK) {
				throw new IOException("HTTP error code: " + responseCode);
			}
			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String  line;
			content = "";
			while ((line = rd.readLine()) != null) {
				content += line + "\n";

			}

		}

		catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		connection.disconnect();
			return content;



}

	protected String getJson(String urls) {

		URL url = null;
		try {
			url = new URL(urls);
			HttpURLConnection connection = null;

			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(false);
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			connection.connect();
			int responseCode = connection.getResponseCode();
			if (responseCode != HttpsURLConnection.HTTP_OK) {
				throw new IOException("HTTP error code: " + responseCode);
			}
			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String  line;
			content = "";
			while ((line = rd.readLine()) != null) {
				content += line + "\n";

			}

		}

		catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;



	}

	protected void onProgressUpdate(Integer... progress) {
	}

	protected void onPostExecute(String result) {
		// this is executed on the main thread after the process is over
		// update your UI here
		Log.d("xml",result);


	}


}
