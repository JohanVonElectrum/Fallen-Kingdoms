package FallenKingdoms.data.types;

import FallenKingdoms.math.Mathf;
import FallenKingdoms.engine.objects.Transform;

public class Matrix4f {

    public static final int SIZE = 4;
    private float[] elements = new float[SIZE * SIZE];

    public static Matrix4f identity() {
        Matrix4f identity = new Matrix4f();

        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE; i++) {
                identity.set(i, j, i == j ? 1 : 0);
            }
        }

        return identity;
    }

    public static Matrix4f translate(Vector3f offset) {
        Matrix4f translate = Matrix4f.identity();

        for (int j = 0; j < 3; j++) {
            translate.set(3, j, offset.getArr()[j]);
        }

        return translate;
    }

    public static Matrix4f rotate(float angle, Vector3f axis) {
        Matrix4f rotate = Matrix4f.identity();

        float cos = (float) Math.cos(Math.toRadians(angle));
        float sin = (float) Math.sin(Math.toRadians(angle));
        float c = 1f - cos;

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                float a = axis.getArr()[i] * axis.getArr()[j];
                float b = axis.getArr()[Mathf.mod(-(i + j), 3)] * ((int)Math.signum(i - j)) * (Mathf.mod(i + j, 2) == 0 ? 1 : -1);
                rotate.set(i, j, a * c + b * sin + (i == j ? cos : 0));
            }
        }

        return rotate;
    }

    public static Matrix4f scale(Vector3f scalar) {
        Matrix4f scale = Matrix4f.identity();

        for (int i = 0; i < 3; i++) {
            scale.set(i, i, scale.get(i, i) * scalar.getArr()[i]);
        }

        return scale;
    }

    public static Matrix4f projection(float fov, float aspect, float near, float far) {
        Matrix4f projection = Matrix4f.identity();

        float tanFOV = (float) Math.tan(Math.toRadians(fov / 2));

        projection.set(0, 0, 1 / (aspect * tanFOV));
        projection.set(1, 1, 1 / tanFOV);
        projection.set(2, 2, -((far + near) / (far - near)));
        projection.set(3, 2, -((2 * far * near) / (far - near)));
        projection.set(2, 3, -1.0f);

        return projection;
    }

    public static Matrix4f ortho(float left, float right, float bottom, float top, float near, float far) {
        Matrix4f matrix = Matrix4f.identity();

        float width = right - left;
        float height = top - bottom;
        float depth = far - near;

        matrix.set(0, 0, 2f / width);
        matrix.set(1, 1, 2f / height);
        matrix.set(2, 2, 2f / depth);
        matrix.set(3, 0, - (right + left) / width);
        matrix.set(3, 1, - (top + bottom) / height);
        matrix.set(3, 2, - (far + near) / depth);

        return matrix;
    }

    public static Matrix4f multiply(Matrix4f a, Matrix4f b) {
        Matrix4f scale = Matrix4f.identity();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                try {
                    scale.set(i, j, a.getRow(j).scaProd(b.getCol(i)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return scale;
    }

    public static Matrix4f transpose(Matrix4f matrix) {
        Matrix4f transposed = Matrix4f.identity();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                transposed.set(j, i, matrix.get(i, j));
            }
        }

        return transposed;
    }

    public float get(int i, int j) {
        return elements[j * SIZE + i];
    }

    public Matrix4f set(int i, int j, float v) {
        elements[j * SIZE + i] = v;
        return this;
    }

    public float[] getAll() {
        return elements;
    }

    public Vector4f getCol(int i) {
        Vector4f col = new Vector4f(0, 0, 0, 0);
        for (int j = 0; j < SIZE; j++) {
            col.components[j] = get(i, j);
        }
        return col;
    }

    public Vector4f getRow(int j) {
        Vector4f row = new Vector4f(0, 0, 0, 0);
        for (int i = 0; i < SIZE; i++) {
            row.components[i] = get(i, j);
        }
        return row;
    }

    public String toString() {
        String str = "";
        for (int j = 0; j < SIZE; j++) {
            String row = "";
            for (int i = 0; i < SIZE; i++) {
                row += (row == "" ? get(i, j) : ", " + get(i, j));
            }
            str += row + (j != SIZE - 1 ? "\n" : "");
        }
        return "-----\n" + str + "\n-----";
    }

}
