package com.github.ladicek.jadopa;

import java.util.List;

/**
 * <p>A first sentence of a documentation comment. It typically has a special meaning in documentation comments.</p>
 *
 * <p>It's possible to retrieve the first sentence of the documentation comment in an {@link #text() unparsed form} or
 * as a {@link #content() list of spans}. The unparsed form is always equal to the textual concatenation of spans.</p>
 */
public interface FirstSentence {
    /** Returns the original unparsed form of this first sentence of the documentation comment. */
    String text();

    /** Returns the content of this first sentence of the documentation comment as a {@link List} of {@link Span}s. */
    List<Span> content();
}
