package com.snap;

import com.google.zxing.client.android.CaptureActivity;

import android.os.Bundle;
import android.view.Menu;

public class ListCards extends CaptureActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_cards);
    }


}
