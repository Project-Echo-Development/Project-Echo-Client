package net.projectecho;

import lombok.Getter;
import net.projectecho.gui.quickzoom.QuickZoom;
import net.projectecho.manager.AddonManager;

import java.awt.*;

@Getter
public enum Echo {
    INSTANCE;
    public final int clientColor = new Color(90, 70, 124, 255).getRGB();
    private AddonManager addonManager;

    public void initialize() {
        addonManager = new AddonManager();
        QuickZoom.init();
    }
}
