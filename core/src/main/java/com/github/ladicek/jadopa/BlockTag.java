package com.github.ladicek.jadopa;

import java.util.List;

/**
 * <p>A block tag; in the documentation comment, it begins at the beginning of a line, looks like
 * <code>&#64;name content</code> and ends either at the end of the entire comment, or at the beginning
 * of another block tag. Can itself contain {@link Text text} and {@link InlineTag inline tags}.</p>
 */
public interface BlockTag extends Tag {
    /**
     * Returns the content of this block tag: the {@link List} of {@link Span}s that follow after the tag name.
     * The spans can only be {@link Text} or {@link InlineTag}s.
     */
    List<Span> content();
}
