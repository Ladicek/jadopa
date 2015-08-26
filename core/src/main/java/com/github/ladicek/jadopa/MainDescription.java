package com.github.ladicek.jadopa;

import java.util.List;

/**
 * <p>The main description part of the documentation comment. It's a free-form text that can possibly contain
 * {@link InlineTag inline tags}.</p>
 *
 * <p>It's possible to retrieve the main description of the documentation comment in an {@link #text() unparsed form} or
 * as a {@link #content() list of spans}. The unparsed form is always equal to the textual concatenation of spans.</p>
 */
public interface MainDescription {
    /** Returns the original unparsed form of this main description of the documentation comment. */
    String text();

    /** Returns the content of this main description of the documentation comment as a {@link List} of {@link Span}s. */
    List<Span> content();
}
