package com.github.ladicek.jadopa;

final class ReferenceImpl implements Reference {
    private final String type;
    private final String member;
    private final String params;

    ReferenceImpl(String type, String member, String params) {
        this.type = type;
        this.member = member;
        this.params = params;
    }

    @Override
    public String type() {
        return type;
    }

    @Override
    public String member() {
        return member;
    }

    @Override
    public String params() {
        return params;
    }
}
