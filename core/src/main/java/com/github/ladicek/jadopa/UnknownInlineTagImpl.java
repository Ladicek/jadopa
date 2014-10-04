package com.github.ladicek.jadopa;

final class UnknownInlineTagImpl implements InlineTag {
    private final String text;
    private final String name;
    private final String content;

    UnknownInlineTagImpl(String text, String name, String content) {
        this.text = text;
        this.name = name;
        this.content = content;
    }

    @Override
    public String text() {
        return text;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String content() {
        return content;
    }
}
