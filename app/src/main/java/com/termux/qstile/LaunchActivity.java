package com.termux.qstile;

import android.app.Activity;
import android.os.Bundle;
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
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
    }
}
