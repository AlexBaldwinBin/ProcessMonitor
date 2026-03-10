package com.termux.qstile;

import android.app.Activity;
import android.os.Bundle;

public class LaunchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Runtime.getRuntime().exec(new String[]{
                "/system/bin/am",
                "startservice",
                "-n", "com.termux/.app.RunCommandService",
                "-a", "com.termux.RUN_COMMAND",
                "--es", "com.termux.RUN_COMMAND_PATH",
                "/data/data/com.termux/files/home/scripts/scan_wrapper.sh",
                "--ez", "com.termux.RUN_COMMAND_BACKGROUND", "false",
                "--ei", "com.termux.RUN_COMMAND_SESSION_ACTION", "0"
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
    }
}
