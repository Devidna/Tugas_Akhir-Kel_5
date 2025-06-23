package com.juaracoding.utils;

public class ScenarioContext {

    private static final ThreadLocal<String> scenarioName = new ThreadLocal<>();

    public static void setScenarioName(String name) {
        scenarioName.set(name);
    }

    public static String getScenarioName() {
        return scenarioName.get();
    }

    public static void remove() {
        scenarioName.remove();
    }
}
