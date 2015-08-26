package com.github.ladicek.jadopa;

import java.util.List;

/**
 * <p>A fully parsed documentation comment. Documentation comments have two parts, both of them optional:</p>
 *
 * <ul>
 *     <li>{@link #mainDescription() main description} &ndash; a special part of the main description is
 *         the {@link #firstSentence() first sentence}</li>
 *     <li>{@link #tagSection() tag section}</li>
 * </ul>
 *
 * <p>If present, these parts always appear in this order.</p>
 *
 * <p>It's also possible to retrieve the documentation comment in an {@link #text() unparsed form} or
 * as a {@link #content() list of spans}. The unparsed form is always equal to the textual concatenation of spans.</p>
 */
public final class DocComment {
    private final FirstSentence firstSentence;
    private final MainDescription mainDescription;
    private final TagSection tagSection;
    private final String text;
    private final List<Span> content;

    DocComment(FirstSentence firstSentence, MainDescription mainDescription, TagSection tagSection,
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

    /** Returns the {@link FirstSentence first sentence} of the documentation comment, if present. */
    public FirstSentence firstSentence() {
        return firstSentence;
    }

    /** Returns the {@link MainDescription main description} of the documentation comment, if present. */
    public MainDescription mainDescription() {
        return mainDescription;
    }

    /** Returns the {@link TagSection tag section} of the documentation comment, if present. */
    public TagSection tagSection() {
        return tagSection;
    }

    /** Returns the unparsed text of the documentation comment. */
    public String text() {
        return text;
    }

    /** Returns the text of the documentation comment as a {@link List} of {@link Span}s. */
    public List<Span> content() {
        return content;
    }
}
