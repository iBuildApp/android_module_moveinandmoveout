package com.ibuildapp.moveinandmoveout.utils;



import java.math.BigDecimal;
import java.util.regex.Pattern;

public abstract class NumberUtils {
    public static final Pattern FLOATING_POINT_PATTERN = fpPattern();

    private static Pattern fpPattern() {
        String decimal = "(?:\\d++(?:\\.\\d*+)?|\\.\\d++)";
        String completeDec = decimal + "(?:[eE][+-]?\\d++)?[fFdD]?";
        String hex = "(?:\\p{XDigit}++(?:\\.\\p{XDigit}*+)?|\\.\\p{XDigit}++)";
        String completeHex = "0[xX]" + hex + "[pP][+-]?\\d++[fFdD]?";
        String fpPattern = "[+-]?(?:NaN|Infinity|" + completeDec + "|" + completeHex + ")";
        return Pattern.compile(fpPattern);
    }
    public static BigDecimal tryParse(String string){
        if (FLOATING_POINT_PATTERN.matcher(string).matches()) {
            // TODO(user): could be potentially optimized, but only with
            // extensive testing
            try {
                return new BigDecimal(string);
            } catch (NumberFormatException e) {
                // Float.parseFloat has changed specs several times, so fall through
                // gracefully
            }
        }
        return null;
    }
}
