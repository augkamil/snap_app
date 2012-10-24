package com.snap;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.FacebookActivity;
import com.facebook.LoginActivity;
import com.facebook.Session;
import com.facebook.SessionState;

public class Login extends FacebookActivity {
	Intent i = null;
	Context context;
	
	// flag for Internet connection status
    boolean isInternetPresent = false;
 
    // Connection detector class
    ConnectionDetector cd;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.login);
      context = getApplicationContext();   
	  
	  
	  // creating connection detector class instance
      cd = new ConnectionDetector(context);
      
      // get Internet status
      isInternetPresent = cd.isConnectingToInternet();

      if(!isInternetPresent){
			  showAlertDialog(Login.this, "Internet Connection",
                    "Włącz internet", true);
      }
      else {
    	  this.openSession();
      }
	  
	}
	
	@Override
	protected void onSessionStateChange(SessionState state, Exception exception) {
	  // user has either logged in or not ...
	  if (state.isOpened()) {
			i = new Intent(context, ScanQR.class);
			startActivity(i);
	  }
	}
	
	@Override
	protected void onResume() {
	    super.onResume();
	    Session session = Session.getActiveSession();
	    if (session == null || session.getState().isClosed()) {
	        session = new Session(this);
	        Session.setActiveSession(session);
	    }

	    // If we already have a valid token, then we can just open the session silently,
	    // otherwise present the splash screen and ask the user to login.
	    if (session.getState().equals(SessionState.CREATED_TOKEN_LOADED)) {
	        // no need to add any fragments here since it will be 
	        // handled in onSessionStateChange
	        session.openForRead(this);
	    } else if (session.isOpened()) {
	        // if the session is already open, try to show the selection fragment
	    	i = new Intent(context, ScanQR.class);
			startActivity(i);
	    } else {

	    }
	}
	
	public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
 
        // Setting Dialog Title
        alertDialog.setTitle(title);
 
        // Setting Dialog Message
        alertDialog.setMessage(message);
 
        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	startActivity(new Intent(Login.this, Login.class));
            }
        });
 
        // Showing Alert Message
        alertDialog.show();
    }
       
}
