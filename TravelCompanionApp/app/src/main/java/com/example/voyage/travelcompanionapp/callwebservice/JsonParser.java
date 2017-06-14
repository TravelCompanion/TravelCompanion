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
	HttpURLConnection connection = null;
	String content;

	protected String doInBackground(String... urls) {

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
		finally{
			connection.disconnect();
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



	/*public InputStream httpClient(InputStream istream, String uri)
			throws IllegalStateException, IOException {
		this.istream = istream;
		this.uri = uri;
		Configuration config = new Configuration();
		URL urlObj = new URL(config.IpDevice() + uri);
		// commandes httpClient
		//HttpClient httpclient = new DefaultHttpClient();


			HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();

		try {
			// Timeout for reading InputStream arbitrarily set to 3000ms.
			urlConnection.setReadTimeout(3000);
			// Timeout for connection.connect() arbitrarily set to 3000ms.
			urlConnection.setConnectTimeout(3000);
			// For this use case, set HTTP method to GET.
			urlConnection.setRequestMethod("GET");
			// Already true by default but setting just in case; needs to be true since this request
			// is carrying an input (response) body.
			urlConnection.setDoInput(true);
			// Open communications link (network traffic occurs here).
			int responseCode = urlConnection.getResponseCode();
			if (responseCode != HttpsURLConnection.HTTP_OK) {
				throw new IOException("HTTP error code: " + responseCode);
			}

			this.istream = urlConnection.getInputStream();
		}

			// Retrieve the response body as an InputStream.
			finally{
			urlConnection.disconnect();
			}


			return istream;




	}*

	public StringBuilder Buffer(InputStream is) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,
				"UTF-8"));

		StringBuilder sb = new StringBuilder();

		String line = null;

		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}

		is.close();
		return sb;
	}*/
}
