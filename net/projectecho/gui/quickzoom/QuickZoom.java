package net.projectecho.gui.quickzoom;

import net.java.games.input.Keyboard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class QuickZoom {

    public static Minecraft mc;
    public static double currentFov;
    public static double baseFov;
    public static final double SCROLL_STEP = 0.04;
    public static final double MIN_ZOOM = 0.02;
    public static final double MAX_ZOOM = 1.0;
    public static double zoomSpeed = 0.02;
    public static double zoomMult = 0.12;
    public static double zoomFactor = 1;
    public static boolean initialized;
    public static boolean zooming;
    public static double fov;
    public KeyBinding bind;

    public static void init() {
        mc = Minecraft.getMinecraft();
        currentFov = mc.gameSettings.fovSetting;
        baseFov = mc.gameSettings.fovSetting;
        initialized = false;
        zooming = false;
    }
}
