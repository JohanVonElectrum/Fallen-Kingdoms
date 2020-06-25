package FallenKingdoms.engine.graphics;

import FallenKingdoms.data.types.Color;
import FallenKingdoms.data.types.Vector2f;
import FallenKingdoms.data.types.Vector3f;

public class Vertex {

    private Vector3f position;
    private Color color;
    private Vector2f textureCoord;

    public Vertex(Vector3f position, Color color, Vector2f textureCoord) {
        this.position = position;
        this.color = color;
        this.textureCoord = textureCoord;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public Vector2f getTextureCoord() {
        return textureCoord;
    }

}
