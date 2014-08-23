package com.github.lands;

import java.util.EnumMap;

public class Thresholds {

    public enum Level {
        LOW,
        MEDIUM,
        HIGH
    }

    public Thresholds(float low, float medium, float high) {
        values.put(Level.LOW, low);
        values.put(Level.MEDIUM, medium);
        values.put(Level.HIGH, high);
    }

    public float get(Level level){
        return values.get(level);
    }

    private EnumMap<Level, Float> values = new EnumMap<Level, Float>(Level.class);

    public boolean isVeryLow(float value){
        return value < get(Level.LOW);
    }

    public boolean isLow(float value){
        return (value >= get(Level.LOW)) && (value < get(Level.MEDIUM));
    }

    public boolean isMedium(float value){
        return (value >= get(Level.MEDIUM)) && (value < get(Level.HIGH));
    }

    public boolean isHigh(float value){
        return value >= get(Level.HIGH);
    }
}
