package com.termux.qstile;

import android.app.Activity;
import android.os.Bundle;

public class LaunchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Runtime.getRuntime().exec(new String[]{
                "/data/data/com.termux/files/usr/bin/bash",
                "-c",
                "/data/data/com.termux/files/home/scripts/scan_wrapper.sh"
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
    }
}
