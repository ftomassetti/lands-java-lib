package com.github.langgen;

import java.util.List;
import java.util.Random;

public class SamplesBasedLanguageFactory {

    private SamplesBasedLanguageFactory(){
        // prevent instantiation
    }

    public static Language getRandomLanguage(Random r){
        List<String>[] pairOfSamples = LanguageSamples.getRandomPairOfSamples(r);
        return LanguageFactory.randomMix(pairOfSamples[0], pairOfSamples[1], r);
    }

    public static Language getRandomLanguage(){
        return getRandomLanguage(new Random());
    }

}
