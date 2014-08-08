package com.github.lands;

import net.razorvine.pickle.Unpickler;
import net.razorvine.pickle.objects.ClassDict;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Utility to load and save data in the Pickle format (typically used by Python
 * applications).
 */
public final class PickleSerialization {

    private PickleSerialization(){
        // Prevent instantiation
    }

    private static FloatMatrix loadFloatMatrix(Map<?, ?> matrixRaw) {
        List<?> matrixRawData = (List<?>)matrixRaw.get("data");
        int height = matrixRawData.size();
        List<?> firstRow = (List<?>)matrixRawData.get(0);
        int width = firstRow.size();

        FloatMatrix matrix = new FloatMatrix(new Dimension(width, height));
        for (int y=0;y<height;y++) {
            List<?> row = (List<?>)matrixRawData.get(y);
            for (int x=0;x<width;x++) {
                double value = (Double)row.get(x);
                matrix.set(x, y, (float)value);
            }
        }
        return matrix;
    }

    private static BooleanMatrix loadBooleanMatrix(List<?> matrixRawData) {
        int height = matrixRawData.size();
        List<?> firstRow = (List<?>)matrixRawData.get(0);
        int width = firstRow.size();

        BooleanMatrix matrix = new BooleanMatrix(new Dimension(width, height));
        for (int y=0;y<height;y++) {
            List<?> row = (List<?>)matrixRawData.get(y);
            for (int x=0;x<width;x++) {
                matrix.set(x, y, (Boolean)row.get(x));
            }
        }
        return matrix;
    }


    private static BiomeMatrix loadBiomeMatrix(List<?> matrixRawData) {
        int height = matrixRawData.size();
        List<?> firstRow = (List<?>)matrixRawData.get(0);
        int width = firstRow.size();

        BiomeMatrix matrix = new BiomeMatrix(new Dimension(width, height));
        for (int y=0;y<height;y++) {
            List<?> row = (List<?>)matrixRawData.get(y);
            for (int x=0;x<width;x++) {
                String valueName = ((String)row.get(x)).replaceAll(" ","_").toUpperCase();
                Biome value = Biome.valueOf(valueName);
                matrix.set(x, y, value);
            }
        }
        return matrix;
    }


    public static World loadWorld(File file) throws IOException {
        Unpickler unpickler = new Unpickler();
        ClassDict worldRaw = (ClassDict)unpickler.load(new FileInputStream(file));

        String name = (String)worldRaw.get("name");
        int width  = (Integer)worldRaw.get("width");
        int height = (Integer)worldRaw.get("height");

        World world = new World(name, new Dimension(width, height));

        world.setElevation(loadFloatMatrix((Map<?, ?>) worldRaw.get("elevation")));
        world.setOcean(loadBooleanMatrix((List<?>) worldRaw.get("ocean")));

        if (worldRaw.containsKey("biome")) {
            world.setBiome(loadBiomeMatrix((List<?>) worldRaw.get("biome")));
        }

        return world;
    }
}
