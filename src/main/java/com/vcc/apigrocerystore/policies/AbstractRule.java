package com.vcc.apigrocerystore.policies;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class AbstractRule<T> {
    @Autowired
    @Qualifier("gsonCustom")
    protected Gson gson;
}
