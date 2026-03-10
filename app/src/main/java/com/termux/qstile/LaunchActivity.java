package com.termux.qstile;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class LaunchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "Step 1: opening Termux", Toast.LENGTH_SHORT).show();

        Intent openTermux = new Intent();
        openTermux.setClassName("com.termux", "com.termux.app.TermuxActivity");
        openTermux.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(openTermux);

        new Handler().postDelayed(() -> {
            Toast.makeText(this, "Step 2: starting service", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClassName("com.termux", "com.termux.app.RunCommandService");
            intent.setAction("com.termux.RUN_COMMAND");
            intent.putExtra("com.termux.RUN_COMMAND_PATH", "/data/data/com.termux/files/home/scripts/scan_wrapper.sh");
            intent.putExtra("com.termux.RUN_COMMAND_BACKGROUND", false);
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            ComponentName cn = startService(intent);
            Toast.makeText(this, cn != null ? "Step 3: OK!" : "Step 3: NULL", Toast.LENGTH_LONG).show();
            new Handler().postDelayed(() -> finish(), 2000);
        }, 3000);
    }
}
