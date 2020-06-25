package tk.hipogriff.fallenkingdoms.engine.debug;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * All supported color values for console
 */
public class ConsoleColor {

    public static ConsoleColor BLACK = new ConsoleColor('0', 0x00);
    /**
     * Represents dark blue
     */
    public static ConsoleColor DARK_BLUE = new ConsoleColor('1', 0x1);
    /**
     * Represents dark green
     */
    public static ConsoleColor DARK_GREEN = new ConsoleColor('2', 0x2);
    /**
     * Represents dark blue (aqua)
     */
    public static ConsoleColor DARK_AQUA = new ConsoleColor('3', 0x3);
    /**
     * Represents dark red
     */
    public static ConsoleColor DARK_RED = new ConsoleColor('4', 0x4);
    /**
     * Represents dark purple
     */
    public static ConsoleColor DARK_PURPLE = new ConsoleColor('5', 0x5);
    /**
     * Represents gold
     */
    public static ConsoleColor GOLD = new ConsoleColor('6', 0x6);
    /**
     * Represents gray
     */
    public static ConsoleColor GRAY = new ConsoleColor('7', 0x7);
    /**
     * Represents dark gray
     */
    public static ConsoleColor DARK_GRAY = new ConsoleColor('8', 0x8);
    /**
     * Represents blue
     */
    public static ConsoleColor BLUE = new ConsoleColor('9', 0x9);
    /**
     * Represents green
     */
    public static ConsoleColor GREEN = new ConsoleColor('a', 0xA);
    /**
     * Represents aqua
     */
    public static ConsoleColor AQUA = new ConsoleColor('b', 0xB);
    /**
     * Represents red
     */
    public static ConsoleColor RED = new ConsoleColor('c', 0xC);
    /**
     * Represents light purple
     */
    public static ConsoleColor LIGHT_PURPLE = new ConsoleColor('d', 0xD);
    /**
     * Represents yellow
     */
    public static ConsoleColor YELLOW = new ConsoleColor('e', 0xE);
    /**
     * Represents white
     */
    public static ConsoleColor WHITE = new ConsoleColor('f', 0xF);
    /**
     * Represents magical characters that change around randomly
     */
    public static ConsoleColor MAGIC = new ConsoleColor('k', 0x10, true);
    /**
     * Makes the text bold.
     */
    public static ConsoleColor BOLD = new ConsoleColor('l', 0x11, true);
    /**
     * Makes a line appear through the text.
     */
    public static ConsoleColor STRIKETHROUGH = new ConsoleColor('m', 0x12, true);
    /**
     * Makes the text appear underlined.
     */
    public static ConsoleColor UNDERLINE = new ConsoleColor('n', 0x13, true);
    /**
     * Makes the text italic.
     */
    public static ConsoleColor ITALIC = new ConsoleColor('o', 0x14, true);
    /**
     * Resets all previous chat colors or formats.
     */
    public static ConsoleColor RESET = new ConsoleColor('r', 0x15);

    /**
     * The special character which prefixes all chat colour codes. Use this if
     * you need to dynamically convert colour codes from your custom format.
     */
    public static final char COLOR_CHAR = '\u00A7';
    private static final Pattern STRIP_COLOR_PATTERN = Pattern.compile("(?i)" + String.valueOf(COLOR_CHAR) + "[0-9A-FK-OR]");

    private final int intCode;
    private final char code;
    private final boolean isFormat;
    private final String toString;

    private ConsoleColor(char code, int intCode) {
        this(code, intCode, false);
    }

    private ConsoleColor(char code, int intCode, boolean isFormat) {
        this.code = code;
        this.intCode = intCode;
        this.isFormat = isFormat;
        this.toString = new String(new char[] {COLOR_CHAR, code});
    }

    public char getChar() {
        return code;
    }

    @Override
    public String toString() {
        return toString;
    }

    /**
     * Checks if this code is a format code as opposed to a color code.
     */
    public boolean isFormat() {
        return isFormat;
    }

    /**
     * Checks if this code is a color code as opposed to a format code.
     */
    public boolean isColor() {
        return !isFormat && this != RESET;
    }

    public static String stripColor(final String input) {
        if (input == null) {
            return null;
        }

        return STRIP_COLOR_PATTERN.matcher(input).replaceAll("");
    }

    public static String translateAlternateColorCodes(char altColorChar, String textToTranslate) {
        char[] b = textToTranslate.toCharArray();
        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == altColorChar && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i+1]) > -1) {
                b[i] = ConsoleColor.COLOR_CHAR;
                b[i+1] = Character.toLowerCase(b[i+1]);
            }
        }
        return new String(b);
    }
}
