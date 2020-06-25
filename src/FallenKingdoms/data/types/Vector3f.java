package FallenKingdoms.data.types;

public class Vector3f extends Vector {

    public Vector3f(float x, float y, float z) {
        super(x, y, z);
    }

    public static final Vector3f ZERO = new Vector3f(0, 0, 0);
    public static final Vector3f ONE = new Vector3f(1.0f, 1.0f, 1.0f);
    public static final Vector3f RIGHT = new Vector3f(1.0f, 0, 0);
    public static final Vector3f UP = new Vector3f(0, 1.0f, 0);
    public static final Vector3f FORWARD = new Vector3f(0, 0, 1.0f);

    public float getX() {
        return components[0];
    }

    public float getY() {
        return components[1];
    }

    public float getZ() {
        return components[2];
    }
}
