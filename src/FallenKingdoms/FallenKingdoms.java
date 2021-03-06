package FallenKingdoms;

import FallenKingdoms.data.types.Color;
import FallenKingdoms.data.types.Vector2f;
import FallenKingdoms.data.types.Vector3f;
import FallenKingdoms.engine.graphics.*;
import FallenKingdoms.engine.io.Input;
import FallenKingdoms.engine.objects.Camera;
import FallenKingdoms.engine.objects.GameObject;
import FallenKingdoms.engine.objects.Transform;
import FallenKingdoms.standard.Folder;
import org.lwjgl.glfw.GLFW;
import FallenKingdoms.engine.io.Window;

public class FallenKingdoms implements Runnable {
    public Thread game_th;
    public Window window;
    public Renderer renderer;
    public Shader shader;
    public final int WIDTH = 1280, HEIGHT = 760;

    public Mesh mesh = new Mesh(new Vertex[] {
            new Vertex(new Vector3f(-0.5f, 0.5f, -0.5f), new Color(1.0f, 0.0f, 0.0f), new Vector2f(0,0)),
            new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f), new Color(0.0f, 1.0f, 0.0f), new Vector2f(0, 1)),
            new Vertex(new Vector3f(0.5f, -0.5f, -0.5f), new Color(0.0f, 0.0f, 1.0f), new Vector2f(1, 1)),
            new Vertex(new Vector3f(0.5f, 0.5f, -0.5f), new Color(1.0f, 1.0f, 0.0f), new Vector2f(1, 0))
    }, new int[] {
            0, 1, 2,
            0, 2, 3
    }, new Material(Folder.TEXTURES + "default.png"));

    public GameObject gameObject = new GameObject(new Transform(new Vector3f(0, 0, -5), Vector3f.ZERO, Vector3f.ONE), mesh);
    public Camera camera = new Camera(new Transform(Vector3f.ZERO, Vector3f.ZERO, Vector3f.ZERO));

    public void start() {
        game_th = new Thread(this, "game");
        game_th.start();
    }

    public void init() {
        window = new Window(WIDTH, HEIGHT, "Hipogriff Kingdoms");
        shader = new Shader("DefaultShader");
        renderer = new Renderer(window, shader);
        window.setBackgroundColor(1.0f, 0.8f, 0.8f);
        window.create();
        mesh.create();
        shader.create();
    }

    public void run() {
        init();

        while (!window.shouldClose() && !window.input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
            update();
            render();

            if (Input.isKeyDown(GLFW.GLFW_KEY_F11)) window.setFullscreen(!window.isFullscreen());
        }
        close();
    }

    private void update() {
        //gameObject.update();
        camera.update();
        window.update();
    }

    private void render() {
        renderer.renderObject(gameObject, camera);
        window.swapBuffers();
    }

    private void close() {
        window.destroy();
        mesh.destroy();
        shader.destroy();

    }

    public static void main(String[] args) {
        new FallenKingdoms().start();
    }

}
