package com.github.lands;

public class BooleanMatrix {
    private final Dimension dimension;
    private final boolean[] data;

    public BooleanMatrix(Dimension dimension){
        this.dimension = dimension;
        this.data = new boolean[(int)dimension.area()];
    }

    public void set(int x, int y, boolean value) {
        this.data[y*dimension.width + x] = value;
    }

    public boolean get(int x, int y) {
        return this.data[y*dimension.width + x];
    }

}
