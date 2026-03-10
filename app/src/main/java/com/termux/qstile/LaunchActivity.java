package com.termux.qstile;

import android.app.Activity;
import android.os.Bundle;
import java.util.Arrays;

public class LaunchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            ProcessBuilder pb = new ProcessBuilder(Arrays.asList(
                "/data/data/com.termux/files/usr/bin/bash",
                "/data/data/com.termux/files/home/scripts/scan_wrapper.sh"
            ));
            pb.environment().put("PATH", 
                "/data/data/com.termux/files/usr/bin:" +
                "/data/data/com.termux/files/usr/bin/applets:" +
                "/system/bin:/system/xbin");
            pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
    }
}
