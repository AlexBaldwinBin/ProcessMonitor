package com.termux.qstile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LaunchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Сначала открываем TermuxActivity чтобы вывести его на foreground
        Intent openTermux = new Intent();
        openTermux.setClassName("com.termux", "com.termux.app.TermuxActivity");
        openTermux.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(openTermux);

        // Через 1 секунду запускаем наш скрипт — Termux уже в foreground
        new Handler().postDelayed(() -> {
            Intent intent = new Intent();
            intent.setClassName("com.termux", "com.termux.app.RunCommandService");
            intent.setAction("com.termux.RUN_COMMAND");
            intent.putExtra("com.termux.RUN_COMMAND_PATH", "/data/data/com.termux/files/home/scripts/scan_wrapper.sh");
            intent.putExtra("com.termux.RUN_COMMAND_BACKGROUND", false);
            intent.putExtra("com.termux.RUN_COMMAND_SESSION_ACTION", "0");
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            startService(intent);
            finish();
        }, 1000);
    }
}
