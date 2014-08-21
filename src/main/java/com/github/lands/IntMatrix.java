package com.github.lands;

public class IntMatrix {
    private final Dimension dimension;
    private final int[] data;

    public IntMatrix(Dimension dimension){
        this.dimension = dimension;
        this.data = new int[(int)dimension.area()];
    }

    public void set(int x, int y, int value) {
        this.data[y*dimension.width + x] = value;
    }

    public int get(int x, int y) {
        return this.data[y*dimension.width + x];
    }

}
