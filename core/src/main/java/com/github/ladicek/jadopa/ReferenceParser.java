package com.github.ladicek.jadopa;

import java.util.regex.Pattern;

final class ReferenceParser {
    private static final Pattern IDENTIFIER = Pattern.compile("\\p{javaUnicodeIdentifierStart}\\p{javaUnicodeIdentifierPart}*");
    private static final Pattern TYPE = Pattern.compile(IDENTIFIER + "(\\." + IDENTIFIER + ")*");

    private final String string;

    ReferenceParser(String string) {
        this.string = string;
    }

    Reference parse() {
        int hash = string.indexOf('#');
        int leftParen = string.indexOf('(');
        int rightParen = string.lastIndexOf(')');

        String type;
        String member;
        String params;
        if (hash < 0) {
            if (leftParen < 0) { // no member, no params -- only "type"
                params = null;
            } else { // no member, but params -- "type(params)"?

            }
        } else {
            if (leftParen < 0) { // no params -- only "type#member"
                params = null;
            } else { // everything -- "type#member(params)"

            }
        }

        return new ReferenceImpl(type, member, params);
    }
}
