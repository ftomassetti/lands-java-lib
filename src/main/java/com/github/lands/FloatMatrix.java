package com.github.lands;

public class FloatMatrix {
    private final Dimension dimension;
    private final float[] data;

    public FloatMatrix(Dimension dimension){
        this.dimension = dimension;
        this.data = new float[(int)dimension.area()];
    }

    public void set(int x, int y, float value) {
        this.data[y*dimension.width + x] = value;
    }

    public float get(int x, int y) {
        return this.data[y*dimension.width + x];
    }

}
