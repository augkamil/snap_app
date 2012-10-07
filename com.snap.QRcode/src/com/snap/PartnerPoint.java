package com.snap;


import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;


public class PartnerPoint {
	
	private GeoPoint geoPoint;
	private int id;
	private String title;
	private String desc;
	private Drawable marker;

	public PartnerPoint(GeoPoint geoPoint, Drawable marker, int id, String title, String desc) {
		super();
		this.geoPoint = geoPoint;
		this.marker = marker;
		this.id = id;
		this.title = title;
		this.desc = desc;
	}

	public GeoPoint getGeoPoint() {
		return geoPoint;
	}
	
	public Drawable getMarker() {
		return marker;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDesc() {
		return desc;
	}

}