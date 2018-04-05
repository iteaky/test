package com.reaxys.test.pojo;

import java.util.Map;

public class Fact {
    private String label;
    private String name;
    private Map<String, String> options;

    public Fact(String label, String name, Map<String, String> options) {
        this.label = label;
        this.name = name;
        this.options = options;
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    private String formatOptions() {

        StringBuilder result = new StringBuilder();

        options.keySet().forEach(key -> result.append(key).append("=").append(options.get(key)).append(", "));
        if (!"".equals(result.toString())) {
            result.delete(result.length() - 2, result.length());
            return ", " + result;
        }

        return result.toString();
    }

    @Override
    public String toString() {
        return label + " (" + name + formatOptions() + ")";
    }
}
