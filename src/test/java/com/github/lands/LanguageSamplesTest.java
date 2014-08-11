package com.github.lands;

import com.github.langgen.LanguageSamples;
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.IOException;

public class LanguageSamplesTest {

    @Test
    public void languageSamplesAreCorrectlyLoaded() throws IOException {
        assertFalse("Language Samples were not loaded correctly", LanguageSamples.getAll().isEmpty());
    }

}
