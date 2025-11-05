package net.projectecho.gui.keystrokes;

import lombok.var;
import net.minecraft.client.Minecraft;
import net.projectecho.Echo;
import net.projectecho.manager.FontManager;
import net.projectecho.utils.RenderingUtils;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class Keystrokes {

    private static final Minecraft mc = Minecraft.getMinecraft();
    public static double opacityF, opacityB, opacityL, opacityR;

    public static void drawKeystrokes(float x, float y) {
        // Ensure opacity variables are initialized
        opacityF = RenderingUtils.progressiveAnimation(opacityF, mc.gameSettings.keyBindForward.pressed ? 255 : 10, 0.2);
        opacityB = RenderingUtils.progressiveAnimation(opacityB, mc.gameSettings.keyBindBack.pressed ? 255 : 10, 0.2);
        opacityL = RenderingUtils.progressiveAnimation(opacityL, mc.gameSettings.keyBindLeft.pressed ? 255 : 10, 0.2);
        opacityR = RenderingUtils.progressiveAnimation(opacityR, mc.gameSettings.keyBindRight.pressed ? 255 : 10, 0.2);

        var settings = mc.gameSettings;
        var keyBindForward = settings.keyBindForward;
        var keyBindBack = settings.keyBindBack;
        var keyBindLeft = settings.keyBindLeft;
        var keyBindRight = settings.keyBindRight;

        var font = FontManager.getFont(17);
        var color = Echo.INSTANCE.getClientColor();

        String forwardKey = Keyboard.getKeyName(keyBindForward.getKeyCode());
        String backKey = Keyboard.getKeyName(keyBindBack.getKeyCode());
        String leftKey = Keyboard.getKeyName(keyBindLeft.getKeyCode());
        String rightKey = Keyboard.getKeyName(keyBindRight.getKeyCode());

        int colorF = new Color((int) opacityF, (int) opacityF, (int) opacityF, 90).getRGB();
        int colorL = new Color((int) opacityL, (int) opacityL, (int) opacityL, 90).getRGB();
        int colorB = new Color((int) opacityB, (int) opacityB, (int) opacityB, 90).getRGB();
        int colorR = new Color((int) opacityR, (int) opacityR, (int) opacityR, 90).getRGB();

        RenderingUtils.drawBorderedRectangle(x + 22, y, x + 42, y + 20, 0.5f, colorF, color);
        RenderingUtils.drawBorderedRectangle(x, y + 22, x + 20, y + 42, 0.5f, colorL, color);
        RenderingUtils.drawBorderedRectangle(x + 22, y + 22, x + 42, y + 42, 0.5f, colorB, color);
        RenderingUtils.drawBorderedRectangle(x + 44, y + 22, x + 64, y + 42, 0.5f, colorR, color);

        font.drawCenteredStringWithShadow(forwardKey, x + 32.5f, y + 6.5f, color);
        font.drawCenteredStringWithShadow(leftKey, x + 10.5f, y + 28.5f, color);
        font.drawCenteredStringWithShadow(backKey, x + 32.5f, y + 28.5f, color);
        font.drawCenteredStringWithShadow(rightKey, x + 54.5f, y + 28.5f, color);
    }
}
