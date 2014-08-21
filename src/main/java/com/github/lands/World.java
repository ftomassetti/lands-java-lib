package com.github.lands;

import org.python.core.PyObject;

/**
 * A World, with all the different characteristics.
 */
public class World {

    private final String name;
    private final Dimension dimension;
    private PyObject pythonObject;
    // always present
    private BooleanMatrix ocean;
    private FloatMatrix elevation;
    private FloatMatrix precipitations;
    private FloatMatrix permeability;
    private FloatMatrix temperature;
    // optional
    private BiomeMatrix biome;
    private FloatMatrix seaDepth;
    private IntMatrix watermap;
    private FloatMatrix humidity;
    private FloatMatrix irrigation;

    public World(PyObject pythonObject, String name, Dimension dimension) {
        this.pythonObject = pythonObject;
        this.name = name;
        this.dimension = dimension;
    }

    public FloatMatrix getPermeability() {
        return permeability;
    }

    public void setPermeability(FloatMatrix permeability) {
        this.permeability = permeability;
    }

    public FloatMatrix getTemperature() {
        return temperature;
    }

    public void setTemperature(FloatMatrix temperature) {
        this.temperature = temperature;
    }

    public BooleanMatrix getOcean() {
        return ocean;
    }

    public void setOcean(BooleanMatrix ocean) {
        this.ocean = ocean;
    }

    public BiomeMatrix getBiome() {
        return biome;
    }

    public void setBiome(BiomeMatrix biome) {
        this.biome = biome;
    }

    @Override
    public String toString() {
        return "World{" +
                "dimension=" + dimension +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * For performance reasons we consider only the name and the dimension.
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        World world = (World) o;

        if (dimension != null ? !dimension.equals(world.dimension) : world.dimension != null) return false;
        if (name != null ? !name.equals(world.name) : world.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (dimension != null ? dimension.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public FloatMatrix getElevation() {
        return elevation;
    }

    public void setElevation(FloatMatrix elevation) {
        this.elevation = elevation;
    }

    public PyObject asPythonObject() {
        return this.pythonObject;
    }

    public FloatMatrix getSeaDepth() {
        return seaDepth;
    }

    public void setSeaDepth(FloatMatrix seaDepth) {
        this.seaDepth = seaDepth;
    }

    public IntMatrix getWatermap() {
        return watermap;
    }

    public void setWatermap(IntMatrix watermap) {
        this.watermap = watermap;
    }

    public void setHumidity(FloatMatrix humidity) {
        this.humidity = humidity;
    }

    public void setIrrigation(FloatMatrix irrigation) {
        this.irrigation = irrigation;
    }

    public FloatMatrix getPrecipitations() {
        return precipitations;
    }

    public void setPrecipitations(FloatMatrix precipitations) {
        this.precipitations = precipitations;
    }

}
