package com.github.ladicek.jadopa;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NeedsPreprocessingTest {
    private Preprocessor preprocessingOf(String string) {
        return new Preprocessor(string);
    }

    @Test
    public void emptyString() {
        assertFalse(preprocessingOf("").isNeeded());
    }

    @Test
    public void singleCharacter() {
        assertFalse(preprocessingOf(" ").isNeeded());
        assertFalse(preprocessingOf("a").isNeeded());
        assertFalse(preprocessingOf("/").isNeeded());
        assertFalse(preprocessingOf("*").isNeeded());
    }

    @Test
    public void twoCharacters() {
        assertFalse(preprocessingOf("  ").isNeeded());
        assertFalse(preprocessingOf(" a").isNeeded());
        assertFalse(preprocessingOf("a ").isNeeded());
        assertFalse(preprocessingOf("aa").isNeeded());
        assertFalse(preprocessingOf("a/").isNeeded());
        assertFalse(preprocessingOf("/a").isNeeded());
        assertFalse(preprocessingOf(" /").isNeeded());
        assertFalse(preprocessingOf("/ ").isNeeded());
        assertFalse(preprocessingOf("//").isNeeded());
        assertFalse(preprocessingOf("*/").isNeeded());
        assertFalse(preprocessingOf("**").isNeeded());
        assertTrue(preprocessingOf("/*").isNeeded());
    }

    @Test
    public void threeCharacters() {
        assertFalse(preprocessingOf("   ").isNeeded());
        assertFalse(preprocessingOf("  a").isNeeded());
        assertFalse(preprocessingOf(" a ").isNeeded());
        assertFalse(preprocessingOf("a  ").isNeeded());
        assertFalse(preprocessingOf("aaa").isNeeded());
        assertFalse(preprocessingOf("aa/").isNeeded());
        assertFalse(preprocessingOf("a/a").isNeeded());
        assertFalse(preprocessingOf("/aa").isNeeded());
        assertFalse(preprocessingOf("  /").isNeeded());
        assertFalse(preprocessingOf(" / ").isNeeded());
        assertFalse(preprocessingOf("/  ").isNeeded());
        assertFalse(preprocessingOf(" //").isNeeded());
        assertFalse(preprocessingOf("// ").isNeeded());
        assertFalse(preprocessingOf("*/ ").isNeeded());
        assertFalse(preprocessingOf(" */").isNeeded());
        assertFalse(preprocessingOf(" **").isNeeded());
        assertFalse(preprocessingOf("** ").isNeeded());
        assertFalse(preprocessingOf("a/*").isNeeded());
        assertFalse(preprocessingOf("//*").isNeeded());
        assertFalse(preprocessingOf("*/*").isNeeded());
        assertTrue(preprocessingOf(" /*").isNeeded());
        assertTrue(preprocessingOf("/* ").isNeeded());
        assertTrue(preprocessingOf("/*a").isNeeded());
        assertTrue(preprocessingOf("/**").isNeeded());
        assertTrue(preprocessingOf("/*/").isNeeded());
    }

    @Test
    public void allResources() throws IOException {
        for (TestResource resource : TestResource.list()) {
            assertTrue(resource.name(), preprocessingOf(resource.docComment()).isNeeded());
            assertFalse(resource.name() + " (preprocessed)", preprocessingOf(resource.preprocessed()).isNeeded());
        }
    }
}
