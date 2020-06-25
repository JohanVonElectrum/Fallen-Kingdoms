package FallenKingdoms.data.types;

public class Vector4f extends Vector{

    public Vector4f(float x, float y, float z, float w) {
        super(x, y, z, w);
    }

    public float getX() {
        return components[0];
    }

    public float getY() {
        return components[1];
    }

    public float getZ() {
        return components[2];
    }

    public float getW() {
        return components[3];
    }

}
