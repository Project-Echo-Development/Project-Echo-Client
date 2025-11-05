package net.projectecho.addon.impl;

import net.projectecho.addon.api.Addon;
import net.projectecho.Echo;

import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.projectecho.gui.toggles.Option;
import net.projectecho.manager.FontManager;
import net.projectecho.utils.FacingUtils;
import org.lwjgl.input.Mouse;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class InfoHud extends Addon {
    public InfoHud() {
        super("infohud", "Info Hud");
    }

    public static Option coordinates = new Option("Coordinates", false), fps = new Option("FPS", false),
            biomeOpt = new Option("Biome", false), direction = new Option("Direction", false), cpsOpt = new Option("CPS", false);
    private final Map<String, String> infoMap = new LinkedHashMap<>();
    private String xPos, yPos, zPos, biome, cps;
    private boolean wasPressed;
    private final Queue<Long> clicks = new LinkedList<>();
    private long lastPress;

    @Override
    public void displayHook() {
        updateInfo();
        renderInfo();
        super.displayHook();
    }

    private void updateInfo() {
        updateCps();
        updatePosition();
        updateBiome();
        updateDirection();
        manageStrings();
    }

    private void renderInfo() {
        int y = 0;
        for (Map.Entry<String, String> entry : infoMap.entrySet()) {
            FontManager.getFont(17).drawStringWithShadow(entry.getValue(), 2, y + 4, Echo.INSTANCE.getClientColor());
            y += 11;
        }
    }

    private void manageStrings() {
        infoMap.clear(); // Clear previous info to refresh with current options

        if (fps.val) {
            infoMap.put("FPS", "FPS: \247f" + Minecraft.getDebugFPS());
        }
        if (coordinates.val) {
            BlockPos pos = mc.thePlayer.getPosition();
            infoMap.put("X", "X: \247f" + pos.getX());
            infoMap.put("Y", "Y: \247f" + pos.getY());
            infoMap.put("Z", "Z: \247f" + pos.getZ());
        }
        if (direction.val) {
            infoMap.put("Facing", "Facing: \247f" + FacingUtils.performActionBasedOnLooking(mc.thePlayer));
        }
        if (biomeOpt.val) {
            infoMap.put("Biome", biome);
        }
        if (cpsOpt.val) {
            infoMap.put("CPS", cps);
        }
    }

    private void updateCps() {
        int click = getClicks();
        boolean pressed = Mouse.isButtonDown(0);
        if (pressed != wasPressed) {
            wasPressed = pressed;
            lastPress = System.currentTimeMillis();
            if (pressed) {
                clicks.add(lastPress);
            }
        } else if (click > 0) {
            click--;
        }
        cps = "CPS: \247f" + click;
    }

    private int getClicks() {
        long currentTime = System.currentTimeMillis();
        clicks.removeIf(time -> time + 1000L < currentTime);
        return clicks.size();
    }

    private void updateBiome() {
        BlockPos pos = mc.getRenderViewEntity().getPosition();
        Chunk chunk = mc.theWorld.getChunkFromBlockCoords(pos);
        biome = "Biome: \247f" + chunk.getBiome(pos, mc.theWorld.getWorldChunkManager()).biomeName;
    }

    private void updatePosition() {
        BlockPos pos = mc.thePlayer.getPosition();
        infoMap.put("X", "X: \247f" + pos.getX());
        infoMap.put("Y", "Y: \247f" + pos.getY());
        infoMap.put("Z", "Z: \247f" + pos.getZ());
    }

    private void updateDirection() {
        String directionStr = "Facing: \247f" + FacingUtils.performActionBasedOnLooking(mc.thePlayer);
        infoMap.put("Facing", directionStr);
    }
}