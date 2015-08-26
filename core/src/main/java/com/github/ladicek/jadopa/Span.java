package com.github.ladicek.jadopa;

/**
 * <p>A generic part of the documentation comment. It's always possible to retrieve its
 * {@link #text() original unparsed form}.</p>
 *
 * <p>A span is always either a {@link Text} or a {@link Tag}.</p>
 */
public interface Span {
    /** Returns the original unparsed form of this span. */
    String text();
}
