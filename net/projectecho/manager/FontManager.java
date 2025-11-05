package net.projectecho.manager;

import net.projectecho.utils.CustomFontRenderer;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class FontManager {
    private static final Map<Integer, CustomFontRenderer> fontCache = new ConcurrentHashMap<>();
    private static final String FONT_LOCATION = "font/arial.ttf", FONT_LOCATION_MED = "font/arialmed.ttf";

    public static CustomFontRenderer getFont(int size) {
        return fontCache.computeIfAbsent(size, FontManager::createFont);
    }

    public static CustomFontRenderer getMedFont(int size) {
        return fontCache.computeIfAbsent(size, FontManager::createMedFont);
    }

    private static CustomFontRenderer createFont(int size) {
        CustomFontRenderer font = null;
        try (InputStream is = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream(FONT_LOCATION))) {
            Font awtFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, size);
            font = new CustomFontRenderer(awtFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        return font;
    }

    private static CustomFontRenderer createMedFont(int size) {
        CustomFontRenderer font = null;
        try (InputStream is = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream(FONT_LOCATION_MED))) {
            Font awtFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, size);
            font = new CustomFontRenderer(awtFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        return font;
    }
}