package com.github.ladicek.jadopa;

import java.util.List;

public final class DocComment {
    private final FirstSentence firstSentence;
    private final MainDescription mainDescription;
    private final TagSection tagSection;
    private final String text;
    private final List<Span> content;

    public DocComment(FirstSentence firstSentence, MainDescription mainDescription, TagSection tagSection,
                      String text, List<Span> content) {
        this.firstSentence = firstSentence;
        this.mainDescription = mainDescription;
        this.tagSection = tagSection;
        this.text = text;
        this.content = content;
    }

    public static DocComment parse(String text) throws IllegalArgumentException {
        return new DocCommentParser(text).parse();
    }

    public FirstSentence firstSentence() {
        return firstSentence;
    }

    public MainDescription mainDescription() {
        return mainDescription;
    }

    public TagSection tagSection() {
        return tagSection;
    }

    public String text() {
        return text;
    }

    public List<Span> content() {
        return content;
    }
}
