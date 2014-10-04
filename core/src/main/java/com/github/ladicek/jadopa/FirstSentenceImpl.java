package com.github.ladicek.jadopa;

import java.util.List;

final class FirstSentenceImpl extends Part implements FirstSentence {
    FirstSentenceImpl(String text, List<Span> content) {
        super(text, content);
    }
}
