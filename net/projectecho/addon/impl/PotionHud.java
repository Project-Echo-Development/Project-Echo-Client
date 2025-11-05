package net.projectecho.addon.impl;

import lombok.var;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.projectecho.Echo;
import net.projectecho.addon.api.Addon;
import net.projectecho.manager.FontManager;

import java.util.HashMap;
import java.util.Map;

public class PotionHud extends Addon {
    public PotionHud() {
        super("ph", "Potion Hud");
    }

    @Override
    public void displayHook() {
        getAndRenderPotions();
        super.displayHook();
    }

    public void getAndRenderPotions() {
        int offset = 0;

        // Cache potion effects and level suffixes
        var activePotionEffects = mc.thePlayer.getActivePotionEffects();
        Map<Integer, String> levelSuffixes = new HashMap<>();
        levelSuffixes.put(0, "");
        levelSuffixes.put(1, " II");
        levelSuffixes.put(2, " III");
        levelSuffixes.put(3, " IV");
        levelSuffixes.put(4, " V");

        for (PotionEffect effect : activePotionEffects) {
            var potion = Potion.potionTypes[effect.getPotionID()];
            var level = I18n.format(potion.getName());

            // Use Map to get the suffix
            String suffix = levelSuffixes.getOrDefault(effect.getAmplifier(), "");
            level += suffix;
            level += ":\247f " + Potion.getDurationString(effect);

            // Draw the potion effect
            FontManager.getFont(17).drawStringWithShadow(level, 2, 120 + offset, Echo.INSTANCE.getClientColor());
            offset += 11;
        }
    }
}
