package com.github.lands;

public class Dimension {

    final int width;
    final int height;

    public Dimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dimension dimension = (Dimension) o;

        if (height != dimension.height) return false;
        if (width != dimension.width) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        return result;
    }

    public long area() {
        return ((long)width)*((long)height);
    }
}
