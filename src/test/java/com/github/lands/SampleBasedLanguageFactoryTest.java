package com.github.lands;

import com.github.langgen.Language;
import com.github.langgen.LanguageSamples;
import com.github.langgen.SamplesBasedLanguageFactory;
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.IOException;

public class SampleBasedLanguageFactoryTest {

    /**
     * Just a basic sanity test to check it produces something not completely broken.
     */
    @Test
    public void sampleBasedLanguageProducesSomething() throws IOException {
        Language l =SamplesBasedLanguageFactory.getRandomLanguage();
        assertNotNull(l);
        // 10 has no special meaning, we just try to get a few names
        for (int i=0; i<10; i++){
            String name = l.name();
            assertNotNull(name);
            assertFalse(name.isEmpty());
        }
    }
}
