package com.github.ladicek.jadopa;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static com.github.ladicek.jadopa.CountTagsTest.countTags;
import static org.junit.Assert.assertEquals;

public class FirstSentenceTest {
    private static final ImmutableMap<String, Integer> TAG_COUNT = ImmutableMap.<String, Integer>builder()
            .put("com.google.common.collect.Collections2", 1)
            .put("com.google.common.collect.Collections2.filter", 1)
            .put("com.google.common.collect.FluentIterable", 2)
            .put("com.google.common.collect.Maps.newHashMap", 1)
            .put("only-tag-section-multiline", 0)
            .put("only-tag-section-singleline", 0)
            .put("simple-multiline-with-period", 0)
            .put("simple-multiline-without-period", 0)
            .put("simple-singleline-with-period", 0)
            .put("simple-singleline-without-period", 0)
            .build();

    private static final ImmutableMap<String, Integer> SPAN_COUNT = ImmutableMap.<String, Integer>builder()
            .put("com.google.common.collect.Collections2", 3)
            .put("com.google.common.collect.Collections2.filter", 3)
            .put("com.google.common.collect.FluentIterable", 4)
            .put("com.google.common.collect.Maps.newHashMap", 3)
            .put("only-tag-section-multiline", 0)
            .put("only-tag-section-singleline", 0)
            .put("simple-multiline-with-period", 1)
            .put("simple-multiline-without-period", 1)
            .put("simple-singleline-with-period", 1)
            .put("simple-singleline-without-period", 1)
            .build();

    private static final ImmutableMap<String, String> TEXT = ImmutableMap.<String, String>builder()
            .put("com.google.common.collect.Collections2",
                    "Provides static methods for working with {@code Collection} instances.")
            .put("com.google.common.collect.Collections2.filter",
                    "Returns the elements of {@code unfiltered} that satisfy a predicate.")
            .put("com.google.common.collect.FluentIterable",
                    "{@code FluentIterable} provides a rich interface for manipulating {@code Iterable} instances in a\nchained fashion.")
            .put("com.google.common.collect.Maps.newHashMap",
                    "Creates a <i>mutable</i>, empty {@code HashMap} instance.")
            .put("only-tag-section-multiline", "")
            .put("only-tag-section-singleline", "")
            .put("simple-multiline-with-period", "A simple documentation comment.")
            .put("simple-multiline-without-period", "A simple documentation comment")
            .put("simple-singleline-with-period", "A simple documentation comment.")
            .put("simple-singleline-without-period", "A simple documentation comment")
            .build();

    @Test
    public void tagCount() {
        for (TestResource resource : TestResource.list()) {
            FirstSentence firstSentence = DocComment.parse(resource.docComment()).firstSentence();
            assertEquals(resource.name(), TAG_COUNT.get(resource.name()).intValue(), countTags(firstSentence.content()));
        }
    }

    @Test
    public void spanCount() {
        for (TestResource resource : TestResource.list()) {
            FirstSentence firstSentence = DocComment.parse(resource.docComment()).firstSentence();
            assertEquals(resource.name(), SPAN_COUNT.get(resource.name()).intValue(), firstSentence.content().size());
        }
    }

    @Test
    public void text() {
        for (TestResource resource : TestResource.list()) {
            FirstSentence firstSentence = DocComment.parse(resource.docComment()).firstSentence();
            assertEquals(resource.name(), TEXT.get(resource.name()), firstSentence.text());
        }
    }
}
