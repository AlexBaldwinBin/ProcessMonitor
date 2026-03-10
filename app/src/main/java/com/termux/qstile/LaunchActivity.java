package com.termux.qstile;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.widget.Toast;

import rikka.shizuku.Shizuku;

public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!Shizuku.pingBinder()) {
            Toast.makeText(this, "Shizuku not running!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        if (Shizuku.checkSelfPermission() != android.content.pm.PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Requesting Shizuku permission...", Toast.LENGTH_SHORT).show();
            Shizuku.requestPermission(1);
            finish();
            return;
        }

        try {
            String cmd = "am startservice -n com.termux/.app.RunCommandService" +
                " -a com.termux.RUN_COMMAND" +
                " --es com.termux.RUN_COMMAND_PATH /data/data/com.termux/files/home/scripts/scan_wrapper.sh" +
                " --ez com.termux.RUN_COMMAND_BACKGROUND false";

            Shizuku.newProcess(new String[]{"sh", "-c", cmd}, null, null);
            Toast.makeText(this, "OK!", Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            Toast.makeText(this, "ERR: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        finish();
    }
}
