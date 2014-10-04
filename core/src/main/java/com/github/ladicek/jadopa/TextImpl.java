package com.github.ladicek.jadopa;

class TextImpl implements Text {
    private final String text;

    TextImpl(String text) {
        this.text = text;
    }

    @Override
    public String text() {
        return text;
    }
}
