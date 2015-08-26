package com.github.ladicek.jadopa;

import java.util.List;

/**
 * <p>The tag section of the documentation comment. It's a sequence of {@link BlockTag block tags}, separated by
 * new lines (which are represented as {@link Text} tags).</p>
 *
 * <p>It's possible to retrieve the tag section of the documentation comment in an {@link #text() unparsed form} or
 * as a {@link #content() list of spans}. The unparsed form is always equal to the textual concatenation of spans.</p>
 */
public interface TagSection {
    /** Returns the original unparsed form of this tag section of the documentation comment. */
    String text();

    /** Returns the content of this tag section of the documentation comment as a {@link List} of {@link Span}s. */
    List<Span> content();
}
