package com.github.langgen;

import java.io.*;
import java.util.*;

public class LanguageSamples {

    private final static Map<String, List<String>> ALL_SAMPLES;

    static {
        ALL_SAMPLES = new HashMap<String, List<String>>();
        // Unfortunately we have to list them because when they are in
        // a Jar there is no way to get the list

        String[] languageNames = new String[]{
            "beowulf",
            "celticmyth",
            "elven",
            "greek",
            "hebrew",
            "italian_cities",
            "japanese",
            "norse",
            "norsemyth",
            "odin",
            "polish",
            "roman",
            "russian",
            "saxon",
            "welsh"
        };

        for (String languageName : languageNames){
            try {
                InputStream is = LanguageSamples.class.getClassLoader().getResourceAsStream("lang_samples/"+languageName+".txt");
                if (is!=null) {
                    ALL_SAMPLES.put(languageName, loadSamples(is));
                } else {
                    // silently fail...
                }
            } catch (IOException e){
                e.printStackTrace();
                // nothing to do
            }
        }
    }

    private static List<String> loadSamples(InputStream is) throws IOException {
        List<String> samples = new ArrayList<String>();
        InputStreamReader isr = new InputStreamReader(is, "ISO-8859-1");
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
