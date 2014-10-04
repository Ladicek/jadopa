package com.github.ladicek.jadopa;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReconstructTextFromSpansTest {
    @Test
    public void firstSentence() {
        for (TestResource resource : TestResource.list()) {
            FirstSentence firstSentence = DocComment.parse(resource.docComment()).firstSentence();
            assertEquals(resource.name(), firstSentence.text(), reconstructTextFromSpans(firstSentence.content()));
        }
    }

    @Test
    public void content() {
        for (TestResource resource : TestResource.list()) {
            DocComment docComment = DocComment.parse(resource.docComment());
            assertEquals(resource.name(), resource.preprocessed(), reconstructTextFromSpans(docComment.content()));
            assertEquals(resource.name(), docComment.text(), reconstructTextFromSpans(docComment.content()));
        }
    }

    private static String reconstructTextFromSpans(List<Span> spans) {
        StringBuilder result = new StringBuilder();
        for (Span span : spans) {
            result.append(span.text());
        }
        return result.toString();
    }
}
