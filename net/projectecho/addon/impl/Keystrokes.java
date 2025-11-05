package net.projectecho.addon.impl;

import lombok.var;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.projectecho.addon.api.Addon;

import static net.projectecho.gui.keystrokes.Keystrokes.drawKeystrokes;

public class Keystrokes extends Addon {
    public Keystrokes() {
        super("keystrokes", "Keystrokes Mod");
    }

    @Override
    public void displayHook() {
        var sr = new ScaledResolution(mc);
        drawKeystrokes(sr.getScaledWidth() - 66, sr.getScaledHeight() - (mc.currentScreen instanceof GuiChat ? 58 : 44));
        super.displayHook();
    }
}
