package com.github.lands;

import net.razorvine.pickle.Unpickler;
import net.razorvine.pickle.objects.ClassDict;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Utility to load and save data in the Pickle format (typically used by Python
 * applications).
 */
public final class PickleSerialization {

    private PickleSerialization(){
        // Prevent instantiation
    }

    private static FloatMatrix loadFloatMatrix(List<?> matrixRawData) {
        int height = matrixRawData.size();
        List<?> firstRow = (List<?>)matrixRawData.get(0);
        int width = firstRow.size();

        FloatMatrix matrix = new FloatMatrix(new Dimension(width, height));
        for (int y=0;y<height;y++) {
            List<?> row = (List<?>)matrixRawData.get(y);
            for (int x=0;x<width;x++) {
                if (row.get(x) instanceof Double) {
                    double value = (Double) row.get(x);
                    matrix.set(x, y, (float) value);
                } else if (row.get(x) instanceof Integer) {
                    double value = (Integer) row.get(x);
                    matrix.set(x, y, (float) value);
                } else {
                    throw new RuntimeException("Unexpected value "+row.get(x));
                }
            }
        }
        return matrix;
    }

    private static IntMatrix loadIntMatrix(List<?> matrixRawData) {
        int height = matrixRawData.size();
        List<?> firstRow = (List<?>)matrixRawData.get(0);
        int width = firstRow.size();

        IntMatrix matrix = new IntMatrix(new Dimension(width, height));
        for (int y=0;y<height;y++) {
            List<?> row = (List<?>)matrixRawData.get(y);
            for (int x=0;x<width;x++) {
                int value = (Integer)row.get(x);
                matrix.set(x, y, value);
            }
        }
        return matrix;
    }

    private static float loadThresholdMax(Object[] thresholdElement) {
        if (thresholdElement.length != 2) {
            throw new RuntimeException("Threshold element expected to have length 2");
        }
        double value = (Double)thresholdElement[1];
        return (float)value;
    }

    private static Thresholds loadThresholds(List<?> thresholdsRaw) {
        if (thresholdsRaw.size() != 4) {
            throw new RuntimeException("Expected size 4 "+thresholdsRaw.size());
        }
        float low = loadThresholdMax((Object[])thresholdsRaw.get(0));
        float med = loadThresholdMax((Object[])thresholdsRaw.get(1));
        float high = loadThresholdMax((Object[])thresholdsRaw.get(2));
        return new Thresholds(low, med, high);
    }

    private static Quantiles loadQuantiles(Map<?, ?> quantilesRaw) {
        Quantiles quantiles = new Quantiles();
        for (Object keyRaw : quantilesRaw.keySet()){
            Object valueRaw = quantilesRaw.get(keyRaw);
            String keyAsString = (String)keyRaw;
            int keyAsInt = Integer.parseInt(keyAsString);
            double value = (Double)valueRaw;
            quantiles.set(keyAsInt, (float)value);
        }
        return quantiles;
    }

    private static FloatMatrix loadFloatMatrix(Map<?, ?> matrixRaw, boolean withThresholds) {
        List<?> matrixRawData = (List<?>)matrixRaw.get("data");
        FloatMatrix baseMatrix = loadFloatMatrix(matrixRawData);
        if (!withThresholds) {
            return baseMatrix;
        } else {
            if (matrixRaw.containsKey("quantiles")) {
                Object quantilesRaw = matrixRaw.get("quantiles");
                if (quantilesRaw instanceof Map) {
                    return new FloatMatrix(baseMatrix, loadQuantiles((Map<?, ?>) quantilesRaw));
                } else {
                    throw new RuntimeException("Unexpected: List or Map, it is "+quantilesRaw);
                }
            } else if (matrixRaw.containsKey("thresholds")) {
                Object thresholdRaw = matrixRaw.get("thresholds");
                if (thresholdRaw instanceof List) {
                    return new FloatMatrix(baseMatrix, loadThresholds((List<?>) thresholdRaw));
                } else {
                    throw new RuntimeException("Unexpected: List or Map, it is "+thresholdRaw);
                }
            } else {
                throw new RuntimeException("No thresholds or quantiles found");
            }
        }
    }

    private static IntMatrix loadIntMatrix(Map<?, ?> matrixRaw) {
        List<?> matrixRawData = (List<?>)matrixRaw.get("data");
        return loadIntMatrix(matrixRawData);
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

    private static PyObject getWorldPythonObject(ClassDict worldRaw)
    {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("import sys");
        interpreter.exec("sys.path.append(\"python/lands/lands\") ");
        interpreter.exec("from lands.world import *");
        interpreter.set("world_raw", worldRaw);
        return interpreter.eval("World.from_dict(world_raw)");
    }

    private interface Loader {
        public void load(World world, Object data);
    }

    private static void loadIfAvailable(World world, ClassDict worldRaw, String name, Loader loader) {
        try {
            if (worldRaw.containsKey(name)){
                loader.load(world, worldRaw.get(name));
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Problem while loading "+name, e);
        }
    }

    public static World loadWorld(final File file) throws IOException, IncorrectFileException {
        Unpickler unpickler = new Unpickler();
        ClassDict worldRaw = (ClassDict)unpickler.load(new FileInputStream(file));
        PyObject pythonWorld = getWorldPythonObject(worldRaw);

        try {
            String name = (String) worldRaw.get("name");
            int width = (Integer) worldRaw.get("width");
            int height = (Integer) worldRaw.get("height");

            World world = new World(pythonWorld, name, new Dimension(width, height));

            world.setElevation(loadFloatMatrix((Map<?, ?>) worldRaw.get("elevation"), false));
            world.setOcean(loadBooleanMatrix((List<?>) worldRaw.get("ocean")));

            if (worldRaw.containsKey("biome")) {
                world.setBiome(loadBiomeMatrix((List<?>) worldRaw.get("biome")));
            }
            loadIfAvailable(world, worldRaw, "temperature", new Loader(){
                @Override
                public void load(World world, Object data) {
                    loadFloatMatrix((Map<?, ?>) data, true);
                }
            });
            if (worldRaw.containsKey("sea_depth")) {
                world.setSeaDepth(loadFloatMatrix((List<?>) worldRaw.get("sea_depth")));
            }
            loadIfAvailable(world, worldRaw, "watermap", new Loader(){
                @Override
                public void load(World world, Object data) {
                    loadFloatMatrix((Map<?, ?>) data, false);
                }
            });
            loadIfAvailable(world, worldRaw, "permeability", new Loader(){
                @Override
                public void load(World world, Object data) {
                    world.setPermeability(loadFloatMatrix((Map<?, ?>) data, false));
                }
            });
            loadIfAvailable(world, worldRaw, "humidity", new Loader(){
                @Override
                public void load(World world, Object data) {
                    world.setHumidity(loadFloatMatrix((Map<?, ?>) data, true));
                }
            });
            loadIfAvailable(world, worldRaw, "irrigation", new Loader(){
                @Override
                public void load(World world, Object data) {
                    world.setIrrigation(loadFloatMatrix((List<?>) data));
                }
            });
            loadIfAvailable(world, worldRaw, "precipitation", new Loader(){
                @Override
                public void load(World world, Object data) {
                    world.setPrecipitations(loadFloatMatrix((Map<?, ?>) data, false));
                }
            });

            return world;
        } catch (Exception e){
            throw new IncorrectFileException(file, e.toString(), e);
        }
    }
}
