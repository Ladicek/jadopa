package com.github.ladicek.jadopa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class TagSectionImpl extends Part implements TagSection {
    private final List<BlockTag> blockTags;

    TagSectionImpl(String text, List<Span> content) {
        super(text, content);

        List<BlockTag> blockTags = new ArrayList<BlockTag>();
        for (Span span : content) {
            if (span instanceof BlockTag) {
                blockTags.add((BlockTag) span);
            }
        }
        this.blockTags = Collections.unmodifiableList(blockTags);
    }

    @Override
    public List<BlockTag> blockTags() {
        return blockTags;
    }
}
