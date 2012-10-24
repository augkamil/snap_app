package com.snap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SelectionFragment extends Fragment {
	Intent i = null;
	Context context;
	
	@Override
	public void onStart() {
		super.onStart();
		context = getActivity(); 
		
		i = new Intent(context, ScanQR.class);
		startActivity(i);
	}



	@Override
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container, Bundle savedInstanceState) {
	    super.onCreateView(inflater, container, savedInstanceState);
	    View view = inflater.inflate(R.layout.selection, 
	            container, false);
	    return view;
	}
}
