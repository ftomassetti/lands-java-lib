package com.github.lands;

/**
 * A World, with all the different characteristics.
 */
public class World {

    private final String name;
    private final Dimension dimension;

    // always present
    private BooleanMatrix ocean;
    private FloatMatrix elevation;

    // optional
    private BiomeMatrix biome;

    public World(String name, Dimension dimension){
        this.name = name;
        this.dimension = dimension;
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
}
