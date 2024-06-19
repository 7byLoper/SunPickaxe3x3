package by.loper.sunpickaxes.util;

import net.md_5.bungee.api.ChatColor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class ColorUtil {
    private final static Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");

    private ColorUtil() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    // Ну, как же без этого?
    // Теперь можно гордо написать, что твой плагин "поддерживает HEX-цвета"

    public static String translate(String message) {
        Matcher matcher = HEX_PATTERN.matcher(message);

        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            StringBuilder replacement = new StringBuilder("§x");

            for (char c : matcher.group(1).toCharArray()) {
                replacement.append("§").append(c);
            }

            matcher.appendReplacement(buffer, replacement.toString());
        }

        matcher.appendTail(buffer);

        return ChatColor.translateAlternateColorCodes('&', buffer.toString());
    }

    public static List<String> translate(List<String> list) {
        return list.stream().map(ColorUtil::translate).collect(Collectors.toList());
    }
}
