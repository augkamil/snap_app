package com.snap;


import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.maps.GeoPoint;


public class GetDataTask extends AsyncTask<String, Integer, ArrayList<PartnerPoint>> {
	
	private Context context = null;
	private ProgressDialog progress;
	Drawable marker;

	GetDataTask(Context context, ProgressDialog progress) {
		this.context = context;
		this.progress = progress;
	}
	
	@Override
	protected void onPreExecute() {
		progress.show();
	}

	@Override
	protected ArrayList<PartnerPoint> doInBackground(String... params) {
		
		// pobieramy dane z API, korzystając z Apache HTTP Client
		HttpClient httpclient = new DefaultHttpClient();
		// ułatwiamy sobie życie
		ResponseHandler<String> responseHandler = new BasicResponseHandler();

		String response = null;
		
		try {
			response = httpclient.execute(new HttpGet(params[0]), responseHandler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.d("pomiar", "pomiar");
		
		ArrayList<PartnerPoint> result = null;
		
		if (response != null) {
	
			JSONObject json = null;
			JSONObject obj = null;
			
			try {
				json = new JSONObject(response);
				
				if (json != null) {
					result = new ArrayList<PartnerPoint>();
					JSONArray partners = json.getJSONArray("partners");
					Log.d("partners", partners.toString());
					
					for (int i = 0; i < partners.length(); i++) {
						
						obj = partners.getJSONObject(i);
						int marker_id = obj.getInt("marker_id");

						switch(marker_id) {
							case 1:
								marker = context.getResources().getDrawable(R.drawable.restaurant);
								break;
							case 2:
								marker = context.getResources().getDrawable(R.drawable.beergarden);
								break;
							case 3:
								marker = context.getResources().getDrawable(R.drawable.billiard);
								break;
							case 4:
								marker = context.getResources().getDrawable(R.drawable.coffee);
								break;
							case 5:
								marker = context.getResources().getDrawable(R.drawable.pizza);
								break;
							default:
								marker = context.getResources().getDrawable(R.drawable.restaurant);
								break;
						}
						
						// konstruujemy obiekt pozycji
						GeoPoint point = new GeoPoint(	(int) (obj.getInt("latitude")), 
														(int) (obj.getInt("longitude")));
						Log.d("WSPÓŁRZĘDNE", ""+point);
		
						
						// dodajemy obiekt miejsca do listy
						result.add(new PartnerPoint(point, 	obj.getInt("id"), 
															obj.getString("name"),
															obj.getString("address"),
															obj.getString("description"),
															marker));
					}			
				}	
				
			} catch (JSONException e) {
				e.printStackTrace();
			}	
		}
		return result;
	}

	@Override
	protected void onPostExecute(ArrayList<PartnerPoint> result) { 
		progress.dismiss();
		//((Map) context).makeOverlay(result);
		((ScanQR) context).result = result;
		Log.d("result", result.get(2).getTitle());
	}

}
