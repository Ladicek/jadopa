package com.github.ladicek.jadopa;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DetailedParserTest {
    @Test
    public void content() {
        TestResource resource = TestResource.get("com.google.common.collect.Collections2");
        List<Span> content = DocComment.parse(resource.docComment()).content();

        assertEquals(11, content.size());

        {
            Span span = content.get(0);
            assertTrue(span instanceof Text);
            assertEquals("Provides static methods for working with ", span.text());
        }

        {
            Span span = content.get(1);
            assertTrue(span instanceof InlineTag);
            assertEquals("{@code Collection}", span.text());
            assertEquals("code", ((InlineTag) span).name());
            assertEquals("Collection", ((InlineTag) span).content());
        }

        {
            Span span = content.get(2);
            assertTrue(span instanceof Text);
            assertEquals(" instances.", span.text());
        }

        {
            Span span = content.get(3);
            assertTrue(span instanceof Text);
            assertEquals("\n\n", span.text());
        }

        {
            Span span = content.get(4);
            assertTrue(span instanceof BlockTag);
            assertEquals("@author Chris Povirk", span.text());
            assertEquals("author", ((BlockTag) span).name());
            assertEquals(1, ((BlockTag) span).content().size());
            assertTrue(((BlockTag) span).content().get(0) instanceof Text);
            assertEquals("Chris Povirk", ((BlockTag) span).content().get(0).text());
        }

        {
            Span span = content.get(5);
            assertTrue(span instanceof Text);
            assertEquals("\n", span.text());
        }

        {
            Span span = content.get(6);
            assertTrue(span instanceof BlockTag);
            assertEquals("@author Mike Bostock", span.text());
            assertEquals("author", ((BlockTag) span).name());
            assertEquals(1, ((BlockTag) span).content().size());
            assertTrue(((BlockTag) span).content().get(0) instanceof Text);
            assertEquals("Mike Bostock", ((BlockTag) span).content().get(0).text());
        }

        {
            Span span = content.get(7);
            assertTrue(span instanceof Text);
            assertEquals("\n", span.text());
        }

        {
            Span span = content.get(8);
            assertTrue(span instanceof BlockTag);
            assertEquals("@author Jared Levy", span.text());
            assertEquals("author", ((BlockTag) span).name());
            assertEquals(1, ((BlockTag) span).content().size());
            assertTrue(((BlockTag) span).content().get(0) instanceof Text);
            assertEquals("Jared Levy", ((BlockTag) span).content().get(0).text());
        }

        {
            Span span = content.get(9);
            assertTrue(span instanceof Text);
            assertEquals("\n", span.text());
        }

        {
            Span span = content.get(10);
            assertTrue(span instanceof BlockTag);
            assertEquals("@since 2.0 (imported from Google Collections Library)", span.text());
            assertEquals("since", ((BlockTag) span).name());
            assertEquals(1, ((BlockTag) span).content().size());
            assertTrue(((BlockTag) span).content().get(0) instanceof Text);
            assertEquals("2.0 (imported from Google Collections Library)", ((BlockTag) span).content().get(0).text());
        }
    }
}
