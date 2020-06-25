package FallenKingdoms.data.types;

public class Color extends Vector3f{

    public Color(float r, float g, float b) {
        super(r, g, b);
    }

    public float getR() {
        return getX();
    }

    public float getG() {
        return getY();
    }

    public float getB() {
        return getZ();
    }
}
