package com.termux.qstile;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class LaunchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            String cmd = "am startservice -n com.termux/.app.RunCommandService" +
                " -a com.termux.RUN_COMMAND" +
                " --es com.termux.RUN_COMMAND_PATH /data/data/com.termux/files/home/scripts/scan_wrapper.sh" +
                " --ez com.termux.RUN_COMMAND_BACKGROUND false";
            Process p = Runtime.getRuntime().exec(new String[]{
                "/data/data/com.termux/files/home/.local/bin/rish",
                "-c", cmd
            });
            int exit = p.waitFor();
            Toast.makeText(this, "exit: " + exit, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "ERR: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        new Handler().postDelayed(() -> finish(), 2000);
    }
}
