package com.github.lands;

public class BiomeMatrix {
    private final Dimension dimension;
    private final Biome[] data;

    public BiomeMatrix(Dimension dimension){
        this.dimension = dimension;
        this.data = new Biome[(int)dimension.area()];
    }

    public void set(int x, int y, Biome value) {
        this.data[y*dimension.width + x] = value;
    }

    public Biome get(int x, int y) {
        return this.data[y*dimension.width + x];
    }

}
