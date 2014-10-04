package com.github.ladicek.jadopa;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PreprocessorTest {
    @Test
    public void allResources() throws IOException {
        for (TestResource resource : TestResource.list()) {
            String preprocessed = new Preprocessor(resource.docComment()).doIfNeeded();
            assertEquals(resource.name(), resource.preprocessed(), preprocessed);
        }
    }
}
