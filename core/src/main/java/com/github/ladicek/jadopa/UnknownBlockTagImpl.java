package com.github.ladicek.jadopa;

import java.util.Collections;
import java.util.List;

final class UnknownBlockTagImpl implements BlockTag {
    private final String text;
    private final String name;
    private final List<Span> content;

    UnknownBlockTagImpl(String text, String name, List<Span> content) {
        this.text = text;
        this.name = name;
        this.content = Collections.unmodifiableList(content);
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
    public List<Span> content() {
        return content;
    }
}
