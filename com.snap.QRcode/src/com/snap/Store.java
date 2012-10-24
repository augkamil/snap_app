package com.snap;

import java.util.ArrayList;

import android.app.Application;


public class Store extends Application {
	
	private ArrayList<PartnerPoint> result = new ArrayList<PartnerPoint>();
	public boolean flag = false;

	public ArrayList<PartnerPoint> getPartnerPoints() {
		return result;
	}
	
	public void setPartnerPoints(ArrayList<PartnerPoint> partners) {
		result = partners;
	}
}
