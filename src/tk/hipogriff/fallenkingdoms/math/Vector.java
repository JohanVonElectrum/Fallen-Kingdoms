package tk.hipogriff.fallenkingdoms.math;

import tk.hipogriff.fallenkingdoms.error.DimensionSizeException;

public class Vector {

    protected int dimension;
    protected float[] components;

    public Vector(float... components) {
        if (components == null || components.length == 0) components = new float[] { 0 };

        this.dimension = components.length;
        this.components = components;
    }

    public int getDimension() {
        return dimension;
    }

    public float[] getComponents() {
        return components;
    }

    public float getComponent(int i) {
        return components[i];
    }

    public void setComponent(float value, int i) {
        components[i] = value;
    }

    public Vector clone() {
        return new Vector(components);
    }

    public Vector Add(Vector other) {
        if (other.getDimension() != dimension) throw new DimensionSizeException("Vectors must have the same dimension.");

        float[] newComponents = getComponents();
        for (int i = 0; i < dimension; i++) {
            newComponents[i] += other.getComponents()[i];
        }

        return new Vector(newComponents);
    }

    public Vector Substract(Vector other) {
        if (other.getDimension() != dimension) throw new DimensionSizeException("Vectors must have the same dimension.");

        float[] newComponents = getComponents();
        for (int i = 0; i < dimension; i++) {
            newComponents[i] -= other.getComponents()[i];
        }

        return new Vector(newComponents);
    }

}
