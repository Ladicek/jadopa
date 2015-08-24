package com.github.ladicek.jadopa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class _TagsByClassOrName {
    private final Map<Class<? extends Tag>, Collection<Tag>> tagsByClass;
    private final Map<String, Collection<Tag>> tagsByName;

    private _TagsByClassOrName(List<Span> spans) {
        Map<Class<? extends Tag>, Collection<Tag>> tagsByClass = new HashMap<Class<? extends Tag>, Collection<Tag>>();
        Map<String, Collection<Tag>> tagsByName = new HashMap<String, Collection<Tag>>();
        for (Span span : spans) {
            if (span instanceof Tag) {
                Tag tag = (Tag) span;

                Class<? extends Tag> tagClass = tag.getClass();
                Collection<Tag> tagsOfClass = tagsByClass.get(tagClass);
                if (tagsOfClass == null) {
                    tagsOfClass = new ArrayList<Tag>();
                    tagsByClass.put(tagClass, tagsOfClass);
                }
                tagsOfClass.add(tag);

                String tagName = tag.name();
                Collection<Tag> tagsOfName = tagsByName.get(tagName);
                if (tagsOfName == null) {
                    tagsOfName = new ArrayList<Tag>();
                    tagsByName.put(tagName, tagsOfName);
                }
                tagsOfName.add(tag);
            }
        }
        this.tagsByClass = Collections.unmodifiableMap(tagsByClass);
        this.tagsByName = Collections.unmodifiableMap(tagsByName);
    }


    public <T extends Tag> Collection<T> tags(Class<T> tagClass) {
        @SuppressWarnings("unchecked")
        Collection<T> tags = (Collection<T>) tagsByClass.get(tagClass);
        return Collections.unmodifiableCollection(tags);
    }

    public Collection<Tag> tags(String tagName) {
        return Collections.unmodifiableCollection(tagsByName.get(tagName));
    }
}
