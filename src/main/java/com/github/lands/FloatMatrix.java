package com.github.lands;

public class FloatMatrix {
    private final Dimension dimension;
    private final float[] data;
    private final Thresholds thresholds;
    private final Quantiles quantiles;

    FloatMatrix(FloatMatrix data, Thresholds thresholds){
        this.dimension = data.dimension;
        this.data = data.data.clone();
        this.thresholds = thresholds;
        this.quantiles = null;
    }

    FloatMatrix(FloatMatrix data, Quantiles quantiles){
        this.dimension = data.dimension;
        this.data = data.data.clone();
        this.thresholds = null;
        this.quantiles = quantiles;
    }

    public FloatMatrix(Dimension dimension, Thresholds thresholds){
        this.dimension = dimension;
        this.data = new float[(int)dimension.area()];
        this.thresholds = thresholds;
        this.quantiles = null;
    }

    public FloatMatrix(Dimension dimension, Quantiles quantiles){
        this.dimension = dimension;
        this.data = new float[(int)dimension.area()];
        this.thresholds = null;
        this.quantiles = quantiles;
    }

    public FloatMatrix(Dimension dimension){
        this.dimension = dimension;
        this.data = new float[(int)dimension.area()];
        this.thresholds = null;
        this.quantiles = null;
    }

    public Thresholds thresholds() {
        if (this.thresholds == null) {
            throw new IllegalStateException();
        } else {
            return this.thresholds;
        }
    }

    public Quantiles quantiles() {
        if (this.quantiles == null) {
            throw new IllegalStateException();
        } else {
            return this.quantiles;
        }
    }

    public void set(int x, int y, float value) {
        this.data[y*dimension.width + x] = value;
    }

    public float get(int x, int y) {
        return this.data[y*dimension.width + x];
    }

}
