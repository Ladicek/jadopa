package com.github.ladicek.jadopa;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CountTagsTest {
    private static final ImmutableMap<String, Integer> MAIN_DESCRIPTION = ImmutableMap.<String, Integer>builder()
            .put("com.google.common.collect.Collections2", 1)
            .put("com.google.common.collect.Collections2.filter", 15)
            .put("com.google.common.collect.FluentIterable", 18)
            .put("com.google.common.collect.Maps.newHashMap", 5)
            .put("only-tag-section-multiline", 0)
            .put("only-tag-section-singleline", 0)
            .put("simple-multiline-with-period", 0)
            .put("simple-multiline-without-period", 0)
            .put("simple-singleline-with-period", 0)
            .put("simple-singleline-without-period", 0)
            .build();

    private static final ImmutableMap<String, Integer> TAG_SECTION = ImmutableMap.<String, Integer>builder()
            .put("com.google.common.collect.Collections2", 4)
            .put("com.google.common.collect.Collections2.filter", 0)
            .put("com.google.common.collect.FluentIterable", 2)
            .put("com.google.common.collect.Maps.newHashMap", 1)
            .put("only-tag-section-multiline", 1)
            .put("only-tag-section-singleline", 1)
            .put("simple-multiline-with-period", 0)
            .put("simple-multiline-without-period", 0)
            .put("simple-singleline-with-period", 0)
            .put("simple-singleline-without-period", 0)
            .build();

    @Test
    public void mainDescription() {
        for (TestResource resource : TestResource.list()) {
            List<Span> spans = DocComment.parse(resource.docComment()).mainDescription().content();
            assertEquals(resource.name(), MAIN_DESCRIPTION.get(resource.name()).intValue(), countTags(spans));
        }
    }

    @Test
    public void tagSection() {
        for (TestResource resource : TestResource.list()) {
            List<Span> spans = DocComment.parse(resource.docComment()).tagSection().content();
            assertEquals(resource.name(), TAG_SECTION.get(resource.name()).intValue(), countTags(spans));
        }
    }

    @Test
    public void entireContent() {
        for (TestResource resource : TestResource.list()) {
            List<Span> spans = DocComment.parse(resource.docComment()).content();
            int inMainDescription = MAIN_DESCRIPTION.get(resource.name());
            int inTagSection = TAG_SECTION.get(resource.name());
            assertEquals(resource.name(), inMainDescription + inTagSection, countTags(spans));
        }
    }

    static int countTags(List<Span> spans) {
        int result = 0;
        for (Span span : spans) {
            if (span instanceof Tag) {
                result++;
            }
        }
        return result;
    }
}
