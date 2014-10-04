package com.github.ladicek.jadopa;

import java.util.Collections;
import java.util.List;

class Part {
    private final String text;
    private final List<Span> content;

    Part(String text, List<Span> content) {
        this.text = text;
        this.content = Collections.unmodifiableList(content);
    }

    public String text() {
        return text;
    }

    public List<Span> content() {
        return content;
    }
}
