package FallenKingdoms.data.types;

public class Vector2f extends Vector {

    public Vector2f(float x, float y) {
        super(x, y);
    }

    public float getX() {
        return components[0];
    }

    public void setX(float x) {
        components[0] = x;
    }

    public float getY() {
        return components[1];
    }

    public void setY(float y) {
        components[1] = y;
    }

}
