package com.termux.qstile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LaunchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent();
        intent.setClassName("com.termux", "com.termux.app.RunCommandService");
        intent.setAction("com.termux.RUN_COMMAND");
        intent.putExtra("com.termux.RUN_COMMAND_PATH", "/data/data/com.termux/files/home/scripts/scan_wrapper.sh");
        intent.putExtra("com.termux.RUN_COMMAND_BACKGROUND", true);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        startService(intent);
        finish();
    }
}
