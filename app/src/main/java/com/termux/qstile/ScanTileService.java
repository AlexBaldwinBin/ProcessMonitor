package com.termux.qstile;

import android.graphics.drawable.Icon;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.content.Intent;
import android.util.Log;

public class ScanTileService extends TileService {

    @Override
    public void onStartListening() {
        super.onStartListening();
        Tile tile = getQsTile();
        if (tile != null) {
            tile.setIcon(Icon.createWithResource(this, R.drawable.ic_tile));
            tile.setLabel("Scan");
            tile.setState(Tile.STATE_ACTIVE);
            tile.updateTile();
        }
    }

    @Override
    public void onTileAdded() {
        super.onTileAdded();
        Tile tile = getQsTile();
        if (tile != null) {
            tile.setIcon(Icon.createWithResource(this, R.drawable.ic_tile));
            tile.setState(Tile.STATE_ACTIVE);
            tile.updateTile();
        }
    }

    @Override
    public void onClick() {
        super.onClick();
        Log.d("QSTILE", "onClick called!");
        Intent intent = new Intent();
        intent.setClassName(
            "com.termux",
            "com.termux.app.RunCommandService"
        );
        intent.setAction("com.termux.RUN_COMMAND");
        intent.putExtra("com.termux.RUN_COMMAND_PATH", "/data/data/com.termux/files/home/scripts/scan_wrapper.sh");
        intent.putExtra("com.termux.RUN_COMMAND_BACKGROUND", true);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        startService(intent);
    }
}
