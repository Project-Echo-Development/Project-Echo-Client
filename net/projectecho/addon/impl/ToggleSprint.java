package net.projectecho.addon.impl;

import lombok.var;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.KeyBinding;
import net.projectecho.Echo;
import net.projectecho.addon.api.Addon;
import net.projectecho.manager.FontManager;

public class ToggleSprint extends Addon {
    public ToggleSprint() {
        super("togglesprint", "Toggle Sprint");
    }

    @Override
    public void entityHook() {
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), isState());
        super.entityHook();
    }

    @Override
    public void displayHook() {
        var scaledResolution = new ScaledResolution(mc);
        var font = FontManager.getFont(16);
        font.drawStringWithShadow("Sprinting \247f(Toggled)", scaledResolution.getScaledWidth() - font.getWidth("Sprinting \247f(Toggled)"), 2, Echo.INSTANCE.getClientColor());
        super.displayHook();
    }
}
