package com.github.ladicek.jadopa;

import java.util.List;

final class TagSectionImpl extends Part implements TagSection {
    TagSectionImpl(String text, List<Span> content) {
        super(text, content);
    }
}
