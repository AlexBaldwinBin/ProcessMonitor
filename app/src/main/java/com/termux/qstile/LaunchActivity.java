package com.termux.qstile;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import java.io.File;

public class LaunchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            File trigger = new File("/data/data/com.termux/files/home/trigger");
            boolean created = trigger.createNewFile();
            Toast.makeText(this, created ? "TRIGGER CREATED" : "EXISTS/FAILED", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "ERR: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        new Handler().postDelayed(() -> finish(), 2000);
    }
}
