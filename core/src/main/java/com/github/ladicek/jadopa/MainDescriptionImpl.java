package com.github.ladicek.jadopa;

import java.util.List;

final class MainDescriptionImpl extends Part implements MainDescription {
    MainDescriptionImpl(String text, List<Span> content) {
        super(text, content);
    }
}
