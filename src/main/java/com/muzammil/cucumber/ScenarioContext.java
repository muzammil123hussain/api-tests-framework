package com.muzammil.cucumber;


import com.muzammil.enums.Context;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private final Map<String, Object> value;

    public ScenarioContext() {
        value = new HashMap<>();
    }

    public void setContext(Context key, Object value) {
        this.value.put(key.toString(), value);
    }

    public Object getContext(Context key) {
        return value.get(key.toString());
    }

}
