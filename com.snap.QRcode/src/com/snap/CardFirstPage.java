package com.snap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.snap.SimpleGestureFilter.SimpleGestureListener;

public class CardFirstPage extends Activity implements SimpleGestureListener{
	private SimpleGestureFilter detector; 
	
	ImageButton mapBtn;
	ImageButton listBtn;
	ImageButton cardBtn;
	
	ImageButton collectBtn;
	ImageButton scanBtn;
	ImageButton findNewBtn;
	
	Context context;
	Intent i = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_first_page);
        
        context = getApplicationContext();
        detector = new SimpleGestureFilter(this,this); 
        
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
        
        /*
        ImageView stamp1 = (ImageView) findViewById(R.id.stamp1);
        ImageView stamp2 = (ImageView) findViewById(R.id.stamp2);
        ImageView stamp3 = (ImageView) findViewById(R.id.stamp3);
        ImageView stamp4 = (ImageView) findViewById(R.id.stamp4);
        ImageView stamp5 = (ImageView) findViewById(R.id.stamp5);
        ImageView stamp6 = (ImageView) findViewById(R.id.stamp6);
        ImageView stamp7 = (ImageView) findViewById(R.id.stamp7);
        ImageView stamp8 = (ImageView) findViewById(R.id.stamp8);
        ImageView stamp9 = (ImageView) findViewById(R.id.stamp9);
*/
    }
    
    @Override 
    public boolean dispatchTouchEvent(MotionEvent me){ 
    	this.detector.onTouchEvent(me);
    	return super.dispatchTouchEvent(me); 
    }
    
    @Override
    public void onSwipe(int direction) {
    	String str = "";
     
    	switch (direction) {
     
    	case SimpleGestureFilter.SWIPE_RIGHT : str = "Swipe Right";
			i = new Intent(context, CardSecondPage.class);
			startActivity(i);
                       break;
    	case SimpleGestureFilter.SWIPE_LEFT :  str = "Swipe Left";
    	i = new Intent(context, CardSecondPage.class);
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

}
