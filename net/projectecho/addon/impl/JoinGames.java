package net.projectecho.addon.impl;

import net.projectecho.addon.api.Addon;
import net.projectecho.gui.join.JoinGamesGui;
import net.projectecho.gui.toggles.EchoGui;

public class JoinGames extends Addon {
    public JoinGames() {
        super("jg", "Join Games");
    }

    @Override
    public void displayHook() {
        if (mc.currentScreen instanceof EchoGui)
            mc.displayGuiScreen(new JoinGamesGui());
        setState(false);
        super.displayHook();
    }
}
