package com.snap;

import java.util.ArrayList;

import android.app.Application;
import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;


public class Store extends Application {
	
	private ArrayList<PartnerPoint> result = new ArrayList<PartnerPoint>();

	public ArrayList<PartnerPoint> getPartnerPoints() {
		return result;
	}
	
	public void setPartnerPoints(ArrayList<PartnerPoint> partners) {
		result = partners;
	}
}
