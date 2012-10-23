package com.snap;


import android.graphics.drawable.Drawable;
import android.location.Location;
import android.util.Log;

import com.google.android.maps.GeoPoint;


public class PartnerPoint {
	
	private GeoPoint geoPoint;
	private int id;
	private String title;
	private String desc;
	private Drawable marker;
	private String address;
	int distance = 0;

	public PartnerPoint(GeoPoint geoPoint, int id, String title, String address, String desc, Drawable marker) {
		super();
		Log.d("newPartner", "x");
		this.geoPoint = geoPoint;
		this.id = id;
		this.title = title;
		this.address = address;
		this.desc = desc;
		this.marker = marker;

	}
	
	public void updateDistance(GeoPoint currentLocation) {
		float[] dist = new float[10];
		Location.distanceBetween(currentLocation.getLatitudeE6() / 1e6, currentLocation.getLongitudeE6() / 1e6, geoPoint.getLatitudeE6() / 1e6, geoPoint.getLongitudeE6() / 1e6, dist);

		distance = (int) dist[0];	
	}

	public GeoPoint getGeoPoint() {
		return geoPoint;
	}
	
	public Drawable getMarker() {
		return marker;
	}
	
	public int getDistance() {
		return distance;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	
	public String getAddress() {
		return address;
	}

	public String getDesc() {
		return desc;
	}

}