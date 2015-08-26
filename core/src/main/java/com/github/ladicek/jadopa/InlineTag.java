package com.github.ladicek.jadopa;

/**
 * <p>An inline tag; in the documentation comment, it looks like <code>&#123;&#64;name content&#125;</code>.
 * Can only contain text, which is why the {@link #content()} method returns {@link String} and not {@link Text}.</p>
 */
public interface InlineTag extends Tag {
    /** Returns the content of this inline tag: the text that follows after the tag name. */
    String content();
}
