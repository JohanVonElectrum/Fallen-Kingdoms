package FallenKingdoms.engine.objects;

import FallenKingdoms.data.types.Matrix4f;
import FallenKingdoms.data.types.Vector3f;

public class Transform {

    private Vector3f translation, rotation, scale;

    public Transform(Vector3f translation, Vector3f rotation, Vector3f scale) {
        this.translation = translation;
        this.rotation = rotation;
        this.scale = scale;
    }

    public Matrix4f getTransformMatrix() {
        Matrix4f transform = Matrix4f.identity();

        Matrix4f translationMatrix = Matrix4f.translate(translation);
        Matrix4f rotationXMatrix = Matrix4f.rotate(rotation.getX(), Vector3f.RIGHT);
        Matrix4f rotationYMatrix = Matrix4f.rotate(rotation.getY(), Vector3f.UP);
        Matrix4f rotationZMatrix = Matrix4f.rotate(rotation.getZ(), Vector3f.FORWARD);
        Matrix4f scaleMatrix = Matrix4f.scale(scale);

        Matrix4f rotationMatrix = Matrix4f.multiply(rotationXMatrix, Matrix4f.multiply(rotationYMatrix, rotationZMatrix));

        transform = Matrix4f.multiply(translationMatrix, Matrix4f.multiply(rotationMatrix, scaleMatrix));

        return transform;
    }

    public Matrix4f getViewMatrix() {
        Matrix4f view = Matrix4f.identity();

        Vector3f negative = translation.multiply(-1).to3f();
        Matrix4f translationMatrix = Matrix4f.translate(negative);
        Matrix4f rotationXMatrix = Matrix4f.rotate(getRotation().getX(), Vector3f.RIGHT);
        Matrix4f rotationYMatrix = Matrix4f.rotate(getRotation().getY(), Vector3f.UP);
        Matrix4f rotationZMatrix = Matrix4f.rotate(getRotation().getZ(), Vector3f.FORWARD);

        Matrix4f rotationMatrix = Matrix4f.multiply(rotationZMatrix, Matrix4f.multiply(rotationYMatrix, rotationXMatrix));

        view = Matrix4f.multiply(translationMatrix, rotationMatrix);

        return view;
    }

    public Vector3f getTranslation() {
        return translation;
    }

    public void setTranslation(Vector3f translation) {
        this.translation = translation;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }
}
