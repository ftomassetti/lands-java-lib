package com.github.langgen;


import javafx.util.Pair;

import java.io.*;
import java.util.*;

public class LanguageSamples {

    private final static Map<String, List<String>> ALL_SAMPLES;

    static {
        ALL_SAMPLES = new HashMap<String, List<String>>();
        File samplesDir = new File("./python/langgen/lang_samples/");
        for (File sampleFile : samplesDir.listFiles()){
            String languageName = sampleFile.getName();
            if (languageName.contains(".")){
                languageName = languageName.substring(0, languageName.indexOf('.'));
            }
            try {
                ALL_SAMPLES.put(languageName, loadSamples(sampleFile));
            } catch (IOException e){
                // nothing to do
            }
        }
    }

    private static List<String> loadSamples(File file) throws IOException {
        List<String> samples = new ArrayList<String>();
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "ISO-8859-1");
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            String sample = line.trim();
            if (sample.length()>0) {
                samples.add(line.trim());
            }
        }
        br.close();
        return samples;
    }

    private LanguageSamples(){
        // prevent instantiation
    }

    public static Map<String, List<String>> getAll(){
        return ALL_SAMPLES;
    }

    public static List<String>[] getRandomPairOfSamples(Random r){
        List<String> names = new LinkedList<String>();
        names.addAll(ALL_SAMPLES.keySet());
        if (names.size()<2){
            throw new IllegalStateException();
        }
        int firstIndex = r.nextInt(names.size());
        int secondIndex = -1;
        while (secondIndex==-1 || secondIndex==firstIndex){
            secondIndex = r.nextInt(names.size());
        }
        return new List[]{ALL_SAMPLES.get(names.get(firstIndex)), ALL_SAMPLES.get(names.get(secondIndex))};
    }
}
