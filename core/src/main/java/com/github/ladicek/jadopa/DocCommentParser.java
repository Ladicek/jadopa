package com.github.ladicek.jadopa;

import java.util.ArrayList;
import java.util.List;

final class DocCommentParser {
    private final String text; // always ends with \0, which doesn't appear elsewhere

    private int index;

    DocCommentParser(String text) {
        if (text.indexOf('\0') >= 0) {
            throw new IllegalArgumentException("Text contains character \\0");
        }

        this.text = new Preprocessor(text).doIfNeeded().concat("\0");
    }

    private FirstSentence parseFirstSentence() {
        index = 0;
        List<Span> spans = new ArrayList<Span>();
        int textSpanBeginning = 0;
        while (!looksAtSentenceEndInMainDescription()) {
            if (looksAtInlineTag()) {
                if (index > textSpanBeginning) {
                    spans.add(new TextImpl(text.substring(textSpanBeginning, index)));
                }
                parseInlineTag(spans);
                textSpanBeginning = index;
            } else {
                index++;
            }
        }
        if (text.charAt(index) == '.') {
            index++;
        }
        if (index > textSpanBeginning) {
            spans.add(new TextImpl(text.substring(textSpanBeginning, index)));
        }
        return new FirstSentenceImpl(text.substring(0, index), spans);
    }

    DocComment parse() throws IllegalArgumentException {
        FirstSentence firstSentence = parseFirstSentence();

        index = 0;

        List<Span> content = new ArrayList<Span>();

        MainDescription mainDescription;
        {
            int mainDescriptionBeginning = index;
            List<Span> mainDescriptionSpans = new ArrayList<Span>();
            parseMainDescription(mainDescriptionSpans);
            String mainDescriptionText = text.substring(mainDescriptionBeginning, index);
            mainDescription = new MainDescriptionImpl(mainDescriptionText, mainDescriptionSpans);
            content.addAll(mainDescriptionSpans);
        }

        TagSection tagSection;
        {
            int tagSectionBeginning = index;
            List<Span> tagSectionSpans = new ArrayList<Span>();
            parseTagSection(tagSectionSpans);
            String tagSectionText = text.substring(tagSectionBeginning, index);
            tagSection = new TagSectionImpl(tagSectionText, tagSectionSpans);
            content.addAll(tagSectionSpans);
        }

        return new DocComment(firstSentence, mainDescription, tagSection, text.substring(0, index), content);
    }

    private void parseMainDescription(List<Span> spans) {
        while (!eoi()) {
            if (looksAtBlockTag()) {
                break;
            } else if (looksAtInlineTag()) {
                parseInlineTag(spans);
            } else {
                int begin = index;
                while (!eoi() && !looksAtInlineTag() && !looksAtBlockTag()) {
                    index++;
                }
                spans.add(new TextImpl(text.substring(begin, index)));
            }
        }
    }

    private void parseInlineTag(List<Span> spans) {
        if (text.charAt(index) != '{') {
            throw new AssertionError();
        }
        if (text.charAt(index + 1) != '@') {
            throw new AssertionError();
        }
        int inlineTagBeginning = index;
        index += 2;

        int nameBeginning = index;
        while (looksAtTagNameCharacter()) {
            index++;
        }
        String name = text.substring(nameBeginning, index);

        while (looksAtWhitespace()) {
            index++;
        }

        int contentBeginning = index;
        while (looksAtInlineTagContentCharacter()) {
            index++;
        }
        int contentEnding = index;
        if (text.charAt(index) == '}') {
            index++;
        }

        InlineTag inlineTag = new UnknownInlineTagImpl(text.substring(inlineTagBeginning, index), name,
                text.substring(contentBeginning, contentEnding));
        spans.add(inlineTag);
    }

    private void parseTagSection(List<Span> spans) {
        while (looksAtBlockTag()) {
            parseBlockTag(spans);
        }
    }

    private void parseBlockTag(List<Span> spans) {
        int whitespaceBeginning = index;
        while (looksAtWhitespace()) {
            index++;
        }
        if (index > whitespaceBeginning) {
            Text whitespace = new TextImpl(text.substring(whitespaceBeginning, index));
            spans.add(whitespace);
        }

        if (text.charAt(index) != '@') {
            throw new AssertionError();
        }
        int blockTagBeginning = index;
        index++;

        int nameBeginning = index;
        while (looksAtTagNameCharacter()) {
            index++;
        }
        String name = text.substring(nameBeginning, index);

        while (looksAtWhitespace()) {
            index++;
        }

        List<Span> content = new ArrayList<Span>();
        parseMainDescription(content);

        BlockTag blockTag = new UnknownBlockTagImpl(text.substring(blockTagBeginning, index), name, content);
        spans.add(blockTag);
    }

    private boolean eoi() {
        return text.charAt(index) == '\0';
    }

    private boolean looksAtSentenceEndInMainDescription() {
        return eoi() || looksAtBlockTag() || text.charAt(index) == '.' && text.charAt(index + 1) <= ' ';
    }

    private boolean looksAtWhitespace() {
        char c = text.charAt(index);
        return c > '\0' &&  c <= ' ';
    }

    private boolean looksAtTagNameCharacter() {
        char c = text.charAt(index);
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '_' || c == '.';
    }

    private boolean looksAtInlineTagContentCharacter() {
        char c = text.charAt(index);
        return c != '\0' && c != '}';
    }

    private boolean looksAtInlineTag() {
        return text.charAt(index) == '{' && text.charAt(index + 1) == '@';
    }

    private boolean looksAtBlockTag() {
        // must begin with a line separator, except if it is at the very beginning
        if (index > 0 && text.charAt(index) != '\n') {
            return false;
        }

        int index = this.index;
        char c = text.charAt(index);
        while (c > '\0' &&  c <= ' ') {
            index++;
            c = text.charAt(index);
        }
        return text.charAt(index) == '@';
    }
}
