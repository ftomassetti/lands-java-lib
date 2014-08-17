package com.github.lands;

import org.python.core.PyObject;

/**
 * A World, with all the different characteristics.
 */
public class World {

    private PyObject pythonObject;

    private final String name;
    private final Dimension dimension;

    public BooleanMatrix getOcean() {
        return ocean;
    }

    public BiomeMatrix getBiome() {
        return biome;
    }

    // always present
    private BooleanMatrix ocean;
    private FloatMatrix elevation;

    // optional
    private BiomeMatrix biome;

    public World(PyObject pythonObject, String name, Dimension dimension){
        this.pythonObject = pythonObject;
        this.name = name;
        this.dimension = dimension;
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

    public void setBiome(BiomeMatrix biome) {
        this.biome = biome;
    }

    public void setOcean(BooleanMatrix ocean) {
        this.ocean = ocean;
    }

    public PyObject asPythonObject()
    {
        return this.pythonObject;
    }

}
