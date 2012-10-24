package com.snap;

import java.util.ArrayList;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.snap.SimpleGestureFilter.SimpleGestureListener;

public class CardSecondPage extends Activity implements SimpleGestureListener{
	private SimpleGestureFilter detector; 
	Context context;
	Intent i = null;
	TextView text;
	int position;
	ArrayList<PartnerPoint> result = null;
	Store appState;
	
	ImageButton mapBtn;
	ImageButton listBtn;
	ImageButton cardBtn;
	
	ImageButton collectBtn;
	ImageButton scanBtn;
	ImageButton findNewBtn;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        result = new ArrayList<PartnerPoint>();
        appState = ((Store)getApplicationContext());
	    result = appState.getPartnerPoints();
        
        setContentView(R.layout.card_second_page);
     
        position = getIntent().getIntExtra("position", 0);
        PartnerPoint p = result.get(position);
        
        context = getApplicationContext();
        detector = new SimpleGestureFilter(this,this); 
        
        text = (TextView)findViewById(R.id.textSecondPage);
        text.setText(p.getTitle());
        
        mapBtn = (ImageButton)findViewById(R.id.map_btn);
        listBtn = (ImageButton)findViewById(R.id.list_btn);
        cardBtn = (ImageButton)findViewById(R.id.card_btn);
        
        collectBtn = (ImageButton)findViewById(R.id.collect_btn); 
        scanBtn = (ImageButton)findViewById(R.id.scan_btn);
        findNewBtn = (ImageButton)findViewById(R.id.find_new_btn);
        
        scanBtn.setImageResource(R.drawable.scan_btn_lq);
        collectBtn.setImageResource(R.drawable.collect_btn_lq_pressed);
        findNewBtn.setImageResource(R.drawable.find_new_btn_lq);
        
        
        mapBtn.setImageResource(R.drawable.map_btn_lq);
        listBtn.setImageResource(R.drawable.list_btn_lq);
        cardBtn.setImageResource(R.drawable.card_btn_pressed_lq);
        
        mapBtn.setOnClickListener(lMap);
        listBtn.setOnClickListener(lList);
        
        scanBtn.setOnClickListener(lScan);
        findNewBtn.setOnClickListener(lFindNew);
    }
    
    @Override 
    public boolean dispatchTouchEvent(MotionEvent me){ 
    	this.detector.onTouchEvent(me);
    	return super.dispatchTouchEvent(me); 
    }
    
    private View.OnClickListener lMap = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			i = new Intent(context, MyMap.class);
			startActivity(i);
		}
	};
	
	private View.OnClickListener lList = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			i = new Intent(context, ListMyCards.class);
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
    
    @Override
    public void onSwipe(int direction) {
    	String str = "";
     
    	switch (direction) {
     
    	case SimpleGestureFilter.SWIPE_RIGHT : str = "Swipe Right";
			i = new Intent(context, CardFirstPage.class);
			startActivity(i);
                       break;
    	case SimpleGestureFilter.SWIPE_LEFT :  str = "Swipe Left";
    	i = new Intent(context, CardFirstPage.class);
		startActivity(i);
                   break;
    	case SimpleGestureFilter.SWIPE_DOWN :  str = "Swipe Down";
                                               break;
    	case SimpleGestureFilter.SWIPE_UP :    str = "Swipe Up";
                                               break;
                                              
     } 
    	//Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDoubleTap() {
    	//Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show(); 
    }

}
