package tk.hipogriff.fallenkingdoms.math;

import java.nio.FloatBuffer;

public class Matrix {

    private Vector[] rows;

    public Matrix(int order) {
        this.rows = getIdentity(order).rows;
    }

    public Matrix(Vector... components) {
        this.rows = components;
    }

    public static Matrix getIdentity(int order) {
        Vector[] rows = new Vector[order];
        for (int i = 0; i < order; i++) {
            float[] components = new float[order];
            components[i] = 1;
            rows[i] = new Vector(components);
        }
        return new Matrix(rows);
    }

    public void getBuffer(FloatBuffer buffer) {
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[i].getDimension(); j++) {
                buffer.put(rows[i].getComponent(j));
            }
        }
    }

    public void set(float value, int row, int column) {
        rows[row].setComponent(value, column);
    }

}
