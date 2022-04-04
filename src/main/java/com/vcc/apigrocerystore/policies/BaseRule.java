package com.vcc.apigrocerystore.policies;

public interface BaseRule<F> {
    void verify(F form) throws Exception;
}