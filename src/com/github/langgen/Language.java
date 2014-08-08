package com.github.langgen;

import org.python.core.PyString;
import org.python.util.PythonInterpreter;

import java.util.List;

public class Language {

    private PythonInterpreter interpreter;

    public Language(List<String> samples) {
        interpreter = new PythonInterpreter();
        interpreter.exec("import sys");
        interpreter.exec("sys.path.append(\"python/langgen\") ");
        interpreter.exec("from langgen.langgen import *");
        interpreter.exec("samples = []");
        for (String sample : samples){
            interpreter.exec("samples.append(\""+sample+"\")");
        }
        interpreter.exec("language = Language.language_from_samples(samples)");

    }

    public String name() {
        return ((PyString)interpreter.eval("language.name()")).asString();
    }

}
