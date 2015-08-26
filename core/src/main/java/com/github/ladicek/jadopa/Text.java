package com.github.ladicek.jadopa;

/**
 * <p>A textual span. Contains no structured information that would be parsed from the documentation
 * comment, it's just a text (e.g. an HTML snippet if the original documentation comment is written in HTML,
 * or a Markdown snippet, etc.).</p>
 */
public interface Text extends Span {
}
