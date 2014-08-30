package com.github.lands;

import com.github.langgen.Language;
import com.github.langgen.LanguageSamples;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class LanguageTest {

    private List<String> samples = new ArrayList<String>();

    @Before
    public void setup() {
        samples.add("Federico");
    }

    @Test
    public void canGetANameWithAtLeastOneSample() throws IOException {
        Language l = new Language(samples);
        String name = l.name();
        assertNotNull(name);
        assertFalse(name.isEmpty());
    }

    @Test
    public void canGetANameAfterAddingASample() throws IOException {
        Language l = new Language(samples);
        l.addSample("Riccardo");
        String name = l.name();
        assertNotNull(name);
        assertFalse(name.isEmpty());
    }
}
