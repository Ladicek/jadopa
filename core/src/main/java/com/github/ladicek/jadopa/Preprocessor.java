package com.github.ladicek.jadopa;

import java.util.regex.Pattern;

/**
 * Performs some preprocessing needed for easier documentation comment parsing. That is:
 *
 * <ul>
 * <li>trim whitespace</li>
 * <li>remove leading {@code /**} (arbitrary number of asterisks)</li>
 * <li>remove trailing {@code *&#47;} (arbitrary number of asterisks)</li>
 * <li>remove leading asterisks (at least one {@code *}) at the beginning of each line, together with any preceding
 * whitespace and at most one following whitespace; no whitespace is removed if the line's first non-whitespace
 * character isn't an asterisk</li>
 * </ul>
 *
 * After preprocessing, the resulting text is the actual value of a documentation comment.
 */
final class Preprocessor {
    private static final Pattern COMMENT_START = Pattern.compile("^/\\*+");
    private static final Pattern COMMENT_END = Pattern.compile("\\*+/$");
    private static final Pattern COMMENT_LINE_BEGINNING = Pattern.compile("^[ \t\f]+\\*+ ?", Pattern.MULTILINE);

    private final String text;

    Preprocessor(String text) {
        this.text = text.trim();
    }

    boolean isNeeded() {
        return text.startsWith("/*");
    }

    String doIfNeeded() {
        if (!isNeeded()) { // fast path
            return text;
        }

        String text = this.text;
        text = COMMENT_START.matcher(text).replaceAll("");
        text = COMMENT_END.matcher(text).replaceAll("");
        text = COMMENT_LINE_BEGINNING.matcher(text).replaceAll("");
        return text.trim();
    }
}
