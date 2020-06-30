package org.example.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.core.ui.AbstractPage;

public class ScenarioContext {

    private Map<String, String> context;

    private List<String> contextList;

    private AbstractPage home;

    public ScenarioContext() {
        context = new HashMap<>();
    }

    public void setContext(final String key, final String value) {
        context.put(key, value);
    }

    public String getValue(final String key) {
        return context.get(key);
    }

}
