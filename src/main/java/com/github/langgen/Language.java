package com.github.langgen;

import org.python.core.PyString;
import org.python.util.PythonInterpreter;

import java.util.LinkedList;
import java.util.List;

/**
 * A Language is built from samples and can be used to generate names
 * resembling the samples.
 */
public class Language {

    private PythonInterpreter interpreter;
    private List<String> samples = new LinkedList<String>();

    // Every time a new sample is created the python language is
    // not immediately update but this variable is instead set to true
    private boolean needAnUpdate = true;

    public Language(List<String> samples) {
        if (samples.isEmpty()) {
            throw new IllegalArgumentException("No samples provided");
        }
        interpreter = new PythonInterpreter();
        interpreter.exec("import sys");
        interpreter.exec("sys.path.append(\"python/langgen\") ");
        interpreter.exec("from langgen.langgen import *");
        interpreter.exec("samples = []");
        for (String sample : samples){
            addSample(sample);
        }
    }

    private void updateIfNecessary() {
        if (needAnUpdate) {
            interpreter.exec("language = Language.language_from_samples(samples)");
            needAnUpdate = false;
        }
    }

    public String name() {
        updateIfNecessary();
        return ((PyString)interpreter.eval("language.name()")).asString();
    }

    public void addSample(String sample) {
        samples.add(sample);
        interpreter.exec("samples.append(\""+sample+"\")");
        needAnUpdate = true;
    }

    /**
     * Samples used to build the language.
     */
    public List<String> getSamples() {
        return new LinkedList<String>(samples);
    }

}
