package com.snap;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class ListCards extends Activity{
	
	ImageButton collectBtn;
	ImageButton scanBtn;
	ImageButton findNewBtn;
	
	Intent i = null;
	Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_cards);
        
        context = getApplicationContext();
        
        collectBtn = (ImageButton)findViewById(R.id.collect_btn); 
        scanBtn = (ImageButton)findViewById(R.id.scan_btn);
        findNewBtn = (ImageButton)findViewById(R.id.find_new_btn);
        
        collectBtn.setOnClickListener(lCollect);
        scanBtn.setOnClickListener(lScan);
    }

    private View.OnClickListener lCollect = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			i = new Intent(context, CardFirstPage.class);
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
}
