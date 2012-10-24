package com.snap;


import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;


public class PartnersOverlay extends ItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
	private Context ctx = null;
	ArrayList<PartnerPoint> partnerPoints = null;
	GeoPoint currentLocation;
	Drawable marker;
	Location location;

	public PartnersOverlay(Context ctx, ArrayList<PartnerPoint> partnerPoints, int flag) {
		super(null);

		this.ctx = ctx;
		this.partnerPoints = partnerPoints;
		Log.d("wysw", "2");

		// bonus! dodajemy ZOO do mapy
		Drawable homeMarker = ctx.getResources().getDrawable(R.drawable.star);
		//marker = ctx.getResources().getDrawable(R.drawable.restaurant);
		homeMarker.setBounds(0, 0, homeMarker.getIntrinsicWidth(), homeMarker.getIntrinsicHeight());
		boundCenter(homeMarker);
		Log.d("wysw", "3");
		if(flag==1)	// MyMap==1, Map==2
			location = ((MyMap) ctx).locationManager.getLastKnownLocation(((MyMap) ctx).bestProvider);
		else
			location = ((Map) ctx).locationManager.getLastKnownLocation(((Map) ctx).bestProvider);
		Log.d("wysw", location.toString());
		currentLocation = new GeoPoint((int)(location.getLatitude() * 1E6), (int)(location.getLongitude() * 1E6));
		//((Map) ctx).map.getController().setCenter(currentLocation);

		OverlayItem homeItem = new OverlayItem(currentLocation, "Tu jesteś", "");
		homeItem.setMarker(homeMarker);
		items.add(homeItem);
		if(flag==1)
			((MyMap) ctx).mapController.animateTo(currentLocation);
		else
			((Map) ctx).mapController.animateTo(currentLocation);

		// wypełniamy listę obiektów na mapie pobranymi biletomatami
		for (int i = 0; i < partnerPoints.size(); i++) {
			Drawable marker = partnerPoints.get(i).getMarker();
			//partnerPoints.get(i).updateDistance(currentLocation);
			//Drawable marker = ctx.getResources().getDrawable(R.drawable.restaurant);
			marker.setBounds(0, 0, marker.getIntrinsicWidth(), marker.getIntrinsicHeight());
			boundCenter(marker);
			OverlayItem item = new OverlayItem(partnerPoints.get(i).getGeoPoint(), partnerPoints.get(i).getTitle(), partnerPoints.get(i).getAddress());
			item.setMarker(marker);
			items.add(item);
		}
		Log.d("wysw", "5");
		// wypełniamy warstwę, co powoduje jej przerysowanie
		populate();
	}


	@Override
	protected OverlayItem createItem(int i) {
		return(items.get(i));
	}

	@Override
	public int size() {
		return(items.size());
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		// wyłączamy rysowanie cienia za markerem
		super.draw(canvas, mapView, false);
	}

	@Override
	protected boolean onTap(int i) {

		// pobieramy własną pozycję i liczymy odległość do biletomatu

		float[] distance = new float[10];
		Location.distanceBetween(currentLocation.getLatitudeE6() / 1e6, currentLocation.getLongitudeE6() / 1e6, items.get(i).getPoint().getLatitudeE6() / 1e6, items.get(i).getPoint().getLongitudeE6() / 1e6, distance);

		// przygotowujemy tekst
		String text = items.get(i).getTitle() + " (" + (int) distance[0] + "m)";
		text += "\n" + items.get(i).getSnippet();

		// wyświetlamy toast o treści pobranej z markera
		Toast toast = Toast.makeText(ctx, text, Toast.LENGTH_LONG);
		// zmieniamy grawitację toastu
		toast.setGravity(android.view.Gravity.TOP, 0, 36);
		toast.show();

		return true;
	}
}