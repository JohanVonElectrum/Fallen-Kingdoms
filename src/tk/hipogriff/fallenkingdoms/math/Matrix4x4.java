package tk.hipogriff.fallenkingdoms.math;

public class Matrix4x4 extends Matrix {

    public Matrix4x4() {
        super(4);
    }

    public static Matrix4x4 ortho(float left, float right, float bottom, float top, float near, float far) {
        Matrix4x4 matrix = new Matrix4x4();

        float width = right - left;
        float height = top - bottom;
        float depth = far - near;

        matrix.set(2f / width, 0, 0);
        matrix.set(2f / height, 1, 1);
        matrix.set(2f / depth, 2, 2);
        matrix.set(- (right + left) / width, 3, 0);
        matrix.set(- (top + bottom) / height, 3, 1);
        matrix.set(- (far + near) / depth, 3, 2);

        return matrix;
    }

}
