package com.termux.qstile;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class LaunchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Intent intent = new Intent();
            intent.setClassName("com.termux", "com.termux.app.RunCommandService");
            intent.setAction("com.termux.RUN_COMMAND");
            intent.putExtra("com.termux.RUN_COMMAND_PATH", "/data/data/com.termux/files/home/scripts/scan_wrapper.sh");
            intent.putExtra("com.termux.RUN_COMMAND_BACKGROUND", false);
            intent.putExtra("com.termux.RUN_COMMAND_SESSION_ACTION", 0);
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            ComponentName cn = startService(intent);
            Toast.makeText(this, cn != null ? "OK: " + cn : "FAILED", Toast.LENGTH_LONG).show();
            Log.d("QSTILE", "startService result: " + cn);
        } catch (Exception e) {
            Toast.makeText(this, "ERROR: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("QSTILE", "error", e);
        }
        finish();
    }
}
