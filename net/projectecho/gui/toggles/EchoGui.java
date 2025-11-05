package net.projectecho.gui.toggles;

import lombok.var;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.projectecho.Echo;
import net.projectecho.addon.impl.BedwarsHud;
import net.projectecho.addon.impl.InfoHud;
import net.projectecho.gui.toggles.button.ToggleButton;
import net.projectecho.utils.RenderingUtils;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EchoGui extends GuiScreen {

    ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
    public List<ToggleButton> addons = new ArrayList<>();
    public float x, y, width, height, sliding;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        width = (float) RenderingUtils.progressiveAnimation(width, 562, 0.6);
        height = (float) RenderingUtils.progressiveAnimation(height, 400, 0.6);
        x = (float) scaledResolution.getScaledWidth() / 2 - (width / 2);
        y = (float) scaledResolution.getScaledHeight() / 2 - 200;
        RenderingUtils.makeCropBox(x, y,
                (float) scaledResolution.getScaledWidth() / 2 + (width / 2),
                (float) scaledResolution.getScaledHeight() / 2 + 200);
        renderBackground();
        for (ToggleButton addon : addons) {
            var hovered = isHovered(mouseX, mouseY, (float) addon.x, (float) addon.y, (float) addon.width, (float) addon.height);
            addon.color = -1;
            addon.drawComponent(mouseX, mouseY, hovered);
        }
        RenderingUtils.destroyCropBox();
    }

    @Override
    public void initGui() {
        x = (float) scaledResolution.getScaledWidth() / 2 - (562 / 2);
        y = (float) scaledResolution.getScaledHeight() / 2 - 200;
        addons.add(new ToggleButton(x + 6, y + 6, 182, 112, this, Echo.INSTANCE.getAddonManager().getAddon("infohud"), -1,
                InfoHud.coordinates, InfoHud.fps, InfoHud.direction, InfoHud.biomeOpt, InfoHud.cpsOpt));
        addons.add(new ToggleButton(x + 190, y + 6, 182, 55, this, Echo.INSTANCE.getAddonManager().getAddon("keystrokes"), -1));
        addons.add(new ToggleButton(x + 190, y + 63, 182, 55, this, Echo.INSTANCE.getAddonManager().getAddon("togglesprint"), -1));
        addons.add(new ToggleButton(x + 374, y + 6, 182, 112, this, Echo.INSTANCE.getAddonManager().getAddon("bw"), -1,
                BedwarsHud.ironOpt, BedwarsHud.goldOpt, BedwarsHud.diamondOpt, BedwarsHud.emeraldOpt, BedwarsHud.ArrowOpt));
        addons.add(new ToggleButton(x + 6, y + 120, 182, 55, this, Echo.INSTANCE.getAddonManager().getAddon("agg"), -1));
        addons.add(new ToggleButton(x + 190, y + 120, 182, 55, this, Echo.INSTANCE.getAddonManager().getAddon("ip"), -1));
        addons.add(new ToggleButton(x + 374, y + 120, 182, 55, this, Echo.INSTANCE.getAddonManager().getAddon("jg"), -1, true));
        addons.add(new ToggleButton(x + 6, y + 177, 182, 55, this, Echo.INSTANCE.getAddonManager().getAddon("ph"), -1));
        addons.add(new ToggleButton(x + 190, y + 177, 182, 55, this, Echo.INSTANCE.getAddonManager().getAddon("cchat"), -1));
        addons.add(new ToggleButton(x + 374, y + 177, 182, 55, this, Echo.INSTANCE.getAddonManager().getAddon("ch"), -1));
    }

    public boolean isHovered(int mouseX, int mouseY, float x, float y, float width, float height) {
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (ToggleButton addon : addons)
            if (isHovered(mouseX, mouseY, (float) addon.x, (float) addon.y, (float) addon.width, (float) addon.height))
                addon.onPressed(mouseButton);
    }

    private void renderBackground() {
        RenderingUtils.drawBorderCorneredRectangle(x, y,
                (float) scaledResolution.getScaledWidth() / 2 + (width / 2),
                (float) scaledResolution.getScaledHeight() / 2 + 200, 1, Color.TRANSLUCENT, -1);
        RenderingUtils.drawBlurredRect(RenderingUtils.BlurType.NORMAL, x, y, (double) scaledResolution.getScaledWidth() / 2 + (width / 2),
                (double) scaledResolution.getScaledHeight() / 2 + 200);
    }
}
