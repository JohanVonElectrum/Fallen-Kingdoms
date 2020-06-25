package FallenKingdoms.engine.objects;

import FallenKingdoms.data.types.Vector3f;
import FallenKingdoms.engine.graphics.Mesh;

public class GameObject {

    private Transform transform;
    private Mesh mesh;

    public GameObject(Transform transform, Mesh mesh) {
        this.transform = transform;
        this.mesh = mesh;
    }

    public void update() {
        try {
            transform.setRotation(transform.getRotation().add(Vector3f.ONE).to3f());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Mesh getMesh() {
        return mesh;
    }

    public Transform getTransform() {
        return transform;
    }
}
