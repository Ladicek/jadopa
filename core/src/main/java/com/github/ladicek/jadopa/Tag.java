package com.github.ladicek.jadopa;

/**
 * <p>A <i>tag</i> in the documentation comment. It's the only form of structure in documentation comments.
 * In addition to {@link Span}'s ability to retrieve the original unparsed text, each tag also always has
 * a {@link #name()}.</p>
 *
 * <p>A tag is always either an {@link InlineTag} or a {@link BlockTag}.</p>
 */
public interface Tag extends Span {
    /**
     * Returns the name of this tag. Tag name is the longest string after {@code @} consisting of letters ({@code a-z}
     * and {@code A-Z}), numbers ({@code 0-9}), underscores ({@code _}) and periods ({@code .}).
     */
    String name();
}
