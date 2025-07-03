package com.juaracoding.utils;

import io.cucumber.java.Scenario;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private static final ThreadLocal<String> scenarioName = new ThreadLocal<>();

    private static final Map<String, Integer> tagCounters = new HashMap<>();
    private static final Map<String, String> tagPrefixMap = new HashMap<>();

    static {
        tagPrefixMap.put("@authentication", "AUTH-WEB-");
        tagPrefixMap.put("@dashboard", "DB-WEB-");
        tagPrefixMap.put("@laporanKehadiran", "LPRK-WEB-");
        tagPrefixMap.put("@laporanSemua", "LPRS-WEB-");
        tagPrefixMap.put("@laporanCuti", "LPRC-WEB-");
    }

    public static void generateScenarioName(Scenario scenario) {
        String originalName = scenario.getName();
        String prefix = "";
        String finalScenarioName = originalName;

        for (String tag : scenario.getSourceTagNames()) {
            if (tagPrefixMap.containsKey(tag)) {
                prefix = tagPrefixMap.get(tag);

                int count = tagCounters.getOrDefault(tag, 0) + 1;
                tagCounters.put(tag, count);

                String formattedNumber = String.format("%03d", count);
                finalScenarioName = prefix + formattedNumber + " - " + originalName;
                break;
            }
        }

        scenarioName.set(finalScenarioName);
    }

    public static String getScenarioName() {
        return scenarioName.get();
    }

    public static void remove() {
        scenarioName.remove();
    }
}
