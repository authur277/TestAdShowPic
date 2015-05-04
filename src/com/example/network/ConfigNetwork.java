package com.example.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class ConfigNetwork {
	
	private static final String NETWORK_TAG = "NETWORK";
	
	private static String configURL = "http://192.168.1.238:8080/Android/config.json";
	
	public static JSONObject config = null;
	
	private static Handler networkHandler = null;
	
	
	static {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Looper.prepare();
				networkHandler = new Handler();
				synchronized (networkHandler) {
					networkHandler.notifyAll();
				}
				Looper.loop();
			}
		}).start();
	}
	
	public static void requestConfigUntilDone() {
		requestConfig(null);
		
		while (config == null) {
			sleepOneSecond();
		}
	}
	
	public static void requestConfig(final Runnable callback) {
		while (networkHandler == null) {
			sleepOneSecond();
		}
		networkHandler.post(new Runnable() {
			@Override
			public void run() {
				try {
					HttpRequestBase request = new HttpGet();
					request.setURI(new URI(configURL));
					
					DefaultHttpClient client = new DefaultHttpClient();
					HttpResponse response = client.execute(request);
					
					InputStream inputStream = response.getEntity().getContent();
					String jsonString = stream2String(inputStream);
					
					JSONObject jsonObject = new JSONObject(jsonString);
					config = jsonObject;
					if (callback != null) callback.run();
				} catch (Exception e) {
					e.printStackTrace();
					config = new JSONObject();
				}
			}
		});
	}
	
	public static String stream2String(InputStream istream) {
		return stream2String(istream, 10 * 1000);
	}
	
	public static String stream2String(InputStream istream, long timeoutMillis) {
		StringBuilder total = new StringBuilder();
		try {
			long maxTimeMillis = System.currentTimeMillis() + timeoutMillis;
			BufferedReader r = new BufferedReader(new InputStreamReader(istream));
			String line;
			while ((line = r.readLine()) != null && maxTimeMillis > System.currentTimeMillis()) {
			    total.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return total.toString();
	}
	
	private static void sleepOneSecond() {
		try {
			Thread.sleep(1 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Log.d(NETWORK_TAG, "Main Thread Sleep 1 second");
	}
}
