package com.termux.qstile;

import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.content.Intent;

public class ScanTileService extends TileService {

    @Override
    public void onStartListening() {
        super.onStartListening();
        Tile tile = getQsTile();
        if (tile != null) {
            tile.setState(Tile.STATE_INACTIVE);
            tile.setLabel("Scan");
            tile.updateTile();
        }
    }

    @Override
    public void onTileAdded() {
        super.onTileAdded();
        Tile tile = getQsTile();
        if (tile != null) {
            tile.setState(Tile.STATE_INACTIVE);
            tile.updateTile();
        }
    }

    @Override
    public void onClick() {
        super.onClick();
        Intent intent = new Intent();
        intent.setClassName(
            "com.termux",
            "com.termux.app.RunCommandService"
        );
        intent.setAction("com.termux.RUN_COMMAND");
        intent.putExtra("com.termux.RUN_COMMAND_PATH", "/data/data/com.termux/files/home/scripts/scan.sh");
        intent.putExtra("com.termux.RUN_COMMAND_BACKGROUND", false);
        startService(intent);
    }
}
