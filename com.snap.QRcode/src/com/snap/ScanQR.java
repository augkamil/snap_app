package com.snap;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.zxing.client.android.CaptureActivity;

public class ScanQR extends CaptureActivity{
	
	ImageButton collectBtn;
	ImageButton scanBtn;
	ImageButton findNewBtn;
	
	Intent i = null;
	Context context;
	ArrayList<PartnerPoint> result;
	Store appState;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Loading...");
        //progress.setIcon(R.drawable.launcher_icon);
        result = new ArrayList<PartnerPoint>(); 
        appState = ((Store)getApplicationContext());
        
        
        //if(!appState.flag) {
        
        	//pobieranie danych z serwera
        	 
        	GetDataTask getDataTask = new GetDataTask(this, progress);
        	getDataTask.execute("http://peaceful-hollows-9449.herokuapp.com/partners.json");
        	//appState.flag = true;
        //}
        
        
           
        setContentView(R.layout.scan_qr);
        context = getApplicationContext();     
        
        collectBtn = (ImageButton)findViewById(R.id.collect_btn); 
        scanBtn = (ImageButton)findViewById(R.id.scan_btn);
        findNewBtn = (ImageButton)findViewById(R.id.find_new_btn);
        
        scanBtn.setImageResource(R.drawable.scan_btn_lq_pressed);
        collectBtn.setImageResource(R.drawable.collect_btn_lq);
        findNewBtn.setImageResource(R.drawable.find_new_btn_lq);
        
        collectBtn.setOnClickListener(lCollect);
        findNewBtn.setOnClickListener(lFindNew);
         
    }
    
    private View.OnClickListener lCollect = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			appState.setPartnerPoints(result);
			
			i = new Intent(context, CardFirstPage.class);
			startActivity(i);
		}
	};      
	
	
	private View.OnClickListener lFindNew = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			appState.setPartnerPoints(result);
			
			i = new Intent(context, ListCards.class);
			startActivity(i);
		}
	};
	
	

}
