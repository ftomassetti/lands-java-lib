package com.github.langgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LanguageFactory {

    private LanguageFactory(){
        // prevent instantiation
    }

    public static Language randomMix(List<String> samplesA, List<String> samplesB) {
        long seed = new Random().nextLong();
        return randomMix(samplesA, samplesB, seed);
    }


    public static Language randomMix(List<String> samplesA, List<String> samplesB, long seed) {
        Random r = new Random(seed);
        return randomMix(samplesA, samplesB, r);
    }

    public static Language randomMix(List<String> samplesA, List<String> samplesB, Random r) {
        float distribution = r.nextFloat();
        return randomMix(samplesA, samplesB, r, distribution);
    }


    public static Language randomMix(List<String> samplesA, List<String> samplesB, Random r, float distribution) {
        int nTotalSamples = 100;
        if (distribution<0 || distribution>1.0){
            throw new IllegalArgumentException("Distribution must be in [0,1]");
        }
        int nSamplesA = (int)(distribution * nTotalSamples);
        int nSamplesB = nTotalSamples - nSamplesA;
        List<String> samples = new ArrayList<String>();
        samples.addAll(getRandomElements(samplesA, nSamplesA, r));
        samples.addAll(getRandomElements(samplesB, nSamplesB, r));
        return new Language(samples);
    }

    /**
     * Duplications are acceptable.
     * @param bucket
     * @param nElements
     * @return
     */
    private static List<String> getRandomElements(List<String> bucket, int nElements, Random r){
        List<String> res = new ArrayList<String>();
        for (int i=0;i<nElements;i++) {
            res.add(bucket.get(r.nextInt(bucket.size())));
        }
        return res;
    }

}
