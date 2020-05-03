package com.space.vopo;

import org.springframework.data.redis.core.ZSetOperations;

public class Typeimpl implements ZSetOperations.TypedTuple<String> {
    private String value;
    private double score;

    public Typeimpl(String value, double score) {
        this.value = value;
        this.score = score;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Double getScore() {
        return score;
    }

    @Override
    public int compareTo(ZSetOperations.TypedTuple<String> stringTypedTuple) {
        Typeimpl os = (Typeimpl) stringTypedTuple;
        return new Double(this.score).compareTo(os.getScore());
    }
}
