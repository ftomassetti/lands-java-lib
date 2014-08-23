package com.github.lands;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Federico Tomassetti
 * @since August 2014
 */
public class Quantiles {

    private Map<Integer, Float> data = new HashMap<Integer, Float>();

    public void set(int key, float value){
        if (key<0 || key>100){
            throw new IllegalArgumentException("Illegal quantile "+key+". Expected a value in [0,100]");
        }
        data.put(key, value);
    }

    public float get(int key){
        if (key<0 || key>100){
            throw new IllegalArgumentException("Illegal quantile "+key+". Expected a value in [0,100]");
        }
        return data.get(key);
    }
}
