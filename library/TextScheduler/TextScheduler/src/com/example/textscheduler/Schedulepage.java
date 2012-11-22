package com.example.textscheduler;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Schedulepage extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedulepage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_schedulepage, menu);
        return true;
    }
}
