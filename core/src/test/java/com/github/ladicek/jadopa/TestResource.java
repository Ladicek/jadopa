package com.github.ladicek.jadopa;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import com.google.common.reflect.ClassPath;

import java.io.IOException;

final class TestResource {
    private static final String DOC_COMMENT_EXTENSION = ".doc-comment";
    private static final String PREPROCESSED_DIR = "preprocessed";
    private static final String PREPROCESSED_EXTENSION = ".preprocessed";

    private final String name;
    private String cachedDocComment;
    private String cachedPreprocessed;

    private TestResource(String name) {
        this.name = name;
    }

    String name() {
        return name;
    }

    String docComment() {
        if (cachedDocComment == null) {
            try {
                cachedDocComment = Resources.toString(
                        Resources.getResource(name + DOC_COMMENT_EXTENSION), Charsets.UTF_8);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return cachedDocComment;
    }

    String preprocessed() {
        if (cachedPreprocessed == null) {
            try {
                cachedPreprocessed = Resources.toString(
                        Resources.getResource(PREPROCESSED_DIR + "/" + name + PREPROCESSED_EXTENSION), Charsets.UTF_8);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return cachedPreprocessed;
    }

    // ---

    private static ImmutableList<TestResource> cachedTestResources;

    static ImmutableList<TestResource> list() {
        if (cachedTestResources != null) {
            return cachedTestResources;
        }

        try {
            ImmutableList.Builder<TestResource> result = ImmutableList.builder();
            ClassPath classPath = ClassPath.from(TestResource.class.getClassLoader());
            for (ClassPath.ResourceInfo resource : classPath.getResources()) {
                String resourceName = resource.getResourceName();
                if (resourceName.endsWith(DOC_COMMENT_EXTENSION)) {
                    String resourceNameWithoutExtension = resourceName.substring(0,
                            resourceName.length() - DOC_COMMENT_EXTENSION.length());
                    result.add(new TestResource(resourceNameWithoutExtension));
                }
            }
            cachedTestResources = result.build();
            return cachedTestResources;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static TestResource get(String name) {
        return new TestResource(name);
    }
}
