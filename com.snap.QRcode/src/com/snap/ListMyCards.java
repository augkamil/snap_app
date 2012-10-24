package com.snap;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;

public class ListMyCards extends Activity{
	
	ImageButton mapBtn;
	ImageButton listBtn;
	ImageButton cardBtn;
	ImageButton collectBtn;
	ImageButton scanBtn;
	ImageButton findNewBtn;
	
	Context context;
	Intent i = null;
	ArrayList<PartnerPoint> result = null;
	Store appState;
	PartnerPointAdapter adapter=null;
	GeoPoint currentLocation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        result = new ArrayList<PartnerPoint>();
        appState = ((Store)getApplicationContext());
	    result = appState.getPartnerPoints();
        
        setContentView(R.layout.list_my_cards);
        
        context = getApplicationContext();
                
        mapBtn = (ImageButton)findViewById(R.id.map_btn);
        listBtn = (ImageButton)findViewById(R.id.list_btn);
        cardBtn = (ImageButton)findViewById(R.id.card_btn);
        
        mapBtn.setImageResource(R.drawable.map_btn_lq);
        listBtn.setImageResource(R.drawable.list_btn_pressed_lq);
        cardBtn.setImageResource(R.drawable.card_btn_lq);
        
        collectBtn = (ImageButton)findViewById(R.id.collect_btn); 
        scanBtn = (ImageButton)findViewById(R.id.scan_btn);
        findNewBtn = (ImageButton)findViewById(R.id.find_new_btn);
        
        scanBtn.setImageResource(R.drawable.scan_btn_lq);
        collectBtn.setImageResource(R.drawable.collect_btn_lq_pressed);
        findNewBtn.setImageResource(R.drawable.find_new_btn_lq);
        
        cardBtn.setOnClickListener(lCard);
        mapBtn.setOnClickListener(lMap);
        scanBtn.setOnClickListener(lScan);
        findNewBtn.setOnClickListener(lFindNew);
        
        ListView list = (ListView)findViewById(R.id.partners);
        
        adapter = new PartnerPointAdapter();
        list.setAdapter(adapter);
        
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
    	String bestProvider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(bestProvider);
        currentLocation = new GeoPoint((int)(location.getLatitude() * 1E6), (int)(location.getLongitude() * 1E6));
		for (int i = 0; i < result.size(); i++) {
			result.get(i).updateDistance(currentLocation);
		}
		Collections.sort(result, new PartnerPointComparator());
    }
    
    public class PartnerPointComparator implements Comparator<PartnerPoint>
    {
		@Override
		public int compare(PartnerPoint left, PartnerPoint right) {
			if(left.getDistance() > right.getDistance())
				return 1;
			else if(left.getDistance() < right.getDistance())
				return -1;
			else
				return 0;
		}
    }

    private View.OnClickListener lCard = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			i = new Intent(context, CardFirstPage.class);
			startActivity(i);
		}
	};
	
	private View.OnClickListener lMap = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			i = new Intent(context, MyMap.class);
			startActivity(i);
		}
	};
	
	private View.OnClickListener lScan = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			i = new Intent(context, ScanQR.class);
			startActivity(i);
		}
	};
	
	private View.OnClickListener lFindNew = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			i = new Intent(context, ListCards.class);
			startActivity(i);
		}
	};
	
	
	class PartnerPointAdapter extends ArrayAdapter<PartnerPoint> {
		PartnerPointAdapter() {
			super(ListMyCards.this, R.layout.list_row, result);
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			PartnerHolder holder = null;	
			final int pos = position;
			
			if(row == null) {
				LayoutInflater inflater = getLayoutInflater();
				row = inflater.inflate(R.layout.list_row, parent, false);
				holder = new PartnerHolder(row);
				row.setTag(holder);
			}
			else {
				holder = (PartnerHolder)row.getTag();
				
			}
			
			row.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					//PartnerPoint p = getItem(pos);
					i = new Intent(ListMyCards.this, CardSecondPage.class);
					i.putExtra("position", pos);
					startActivity(i);
				}

			});
			
			holder.populateFrom(result.get(position));
			return(row);
		}
	}
	
	static class PartnerHolder {
		private TextView name=null;
		private TextView address=null;
		private TextView distance=null;
		private TextView points=null;
		private ImageView icon=null;
		
		PartnerHolder(View row) {
			name=(TextView)row.findViewById(R.id.title);
			address=(TextView)row.findViewById(R.id.address);
			distance=(TextView)row.findViewById(R.id.distance);
			points=(TextView)row.findViewById(R.id.points);
			icon=(ImageView)row.findViewById(R.id.icon);
		}
		
		void populateFrom(PartnerPoint p) {
			name.setText(p.getTitle());
			address.setText(p.getAddress());
			distance.setText(p.getDistance()+" m");
			points.setText("0"); //fake
			icon.setImageDrawable(p.getMarker());
		}
	}

}
