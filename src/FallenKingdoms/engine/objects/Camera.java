package FallenKingdoms.engine.objects;

import FallenKingdoms.data.types.Vector;
import FallenKingdoms.data.types.Vector3f;
import FallenKingdoms.engine.io.Input;
import org.lwjgl.glfw.GLFW;

public class Camera extends GameObject {

    float movSpeed = 1f;

    public Camera(Transform transform) {
        super(transform, null);
    }

    public void update() {
        try {
            if (Input.isKeyDown(GLFW.GLFW_KEY_A)) getTransform().setTranslation(getTransform().getTranslation().add(Vector3f.RIGHT.multiply(-movSpeed)).to3f());
            if (Input.isKeyDown(GLFW.GLFW_KEY_D)) getTransform().setTranslation(getTransform().getTranslation().add(Vector3f.RIGHT.multiply(movSpeed)).to3f());
            if (Input.isKeyDown(GLFW.GLFW_KEY_W)) getTransform().setTranslation(getTransform().getTranslation().add(Vector3f.UP.multiply(-movSpeed)).to3f());
            if (Input.isKeyDown(GLFW.GLFW_KEY_S)) getTransform().setTranslation(getTransform().getTranslation().add(Vector3f.UP.multiply(movSpeed)).to3f());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
