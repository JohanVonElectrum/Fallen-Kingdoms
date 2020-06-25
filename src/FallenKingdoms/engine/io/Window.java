package FallenKingdoms.engine.io;

import FallenKingdoms.data.types.Color;
import FallenKingdoms.data.types.Matrix4f;
import FallenKingdoms.data.types.Rect;
import FallenKingdoms.data.types.Vector2Int;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.nio.IntBuffer;

public class Window {

    private int width, height;
    private String title;
    private long window;

    public int frames;
    public long time;

    public Input input;

    private Color backgroundColor;

    private GLFWWindowSizeCallback sizeCallback;

    private boolean isResized;
    private boolean isFullscreen;
    private Rect screenResolution;
    private int[] windowPosX = new int[1], windowPosY = new int[1];

    private Matrix4f projectionMatrix;

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;

        //projectionMatrix = Matrix4f.projection(70.0f, (float) width / (float) height, 0.1f, 1000.0f);
        projectionMatrix = Matrix4f.ortho(0, width, height, 0, -100, 100);
    }

    public void create() {
        if (!GLFW.glfwInit()) {
            System.err.println("ERROR: GLFW wasn't initialized!");
            System.exit(-1);
        }

        input = new Input();
        window = GLFW.glfwCreateWindow(isFullscreen ? 1920 : width, isFullscreen ? 1080 : height, title, isFullscreen ? GLFW.glfwGetPrimaryMonitor() : 0, 0);

        if (window == 0) {
            System.err.println("ERROR: window wasn't created!");
            System.exit(-1);
        }

        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        screenResolution = new Rect(videoMode.width(), videoMode.height());
        windowPosX[0] = (screenResolution.width - width) / 2;
        windowPosY[0] = (screenResolution.height - height) / 2;
        GLFW.glfwSetWindowPos(window, windowPosX[0], windowPosY[0]);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        createCallbacks();

        GLFW.glfwShowWindow(window);

        GLFW.glfwSwapInterval(1);

        time = System.currentTimeMillis();
    }

    private void createCallbacks() {
        sizeCallback = new GLFWWindowSizeCallback() {
            public void invoke(long window, int w, int h) {
                width = w;
                height = h;
                isResized = true;
            }
        };

        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
        GLFW.glfwSetCursorPosCallback(window, input.getMouseMoveCallback());
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonsCallback());
        GLFW.glfwSetScrollCallback(window, input.getMouseScrollCallback());
        GLFW.glfwSetWindowSizeCallback(window, sizeCallback);
    }

    public void update() {
        if (isResized) {
            GL11.glViewport(0, 0, width, height);
            isResized = false;
        }
        GL11.glClearColor(backgroundColor.getR(), backgroundColor.getG(), backgroundColor.getB(), 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GLFW.glfwPollEvents();

        frames++;
        if (System.currentTimeMillis() > time + 1000) {
            GLFW.glfwSetWindowTitle(window, "Hipogriff Kingdoms - FPS: " + frames);
            time = System.currentTimeMillis();
            frames = 0;
        }
    }

    public void swapBuffers() {
        GLFW.glfwSwapBuffers(window);
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(window);
    }

    public void destroy() {
        input.destroy();
        sizeCallback.free();
        GLFW.glfwSetWindowShouldClose(window, true);
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    public void setBackgroundColor(float r, float g, float b) {
        backgroundColor = new Color(r, g, b);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public long getWindow() {
        return window;
    }

    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    public boolean isFullscreen() {
        return isFullscreen;
    }

    public void setFullscreen(boolean fullscreen) {
        this.isFullscreen = fullscreen;
        isResized = true;

        if (isFullscreen) {
            GLFW.glfwGetWindowPos(window, windowPosX, windowPosY);
            GLFW.glfwSetWindowMonitor(window, GLFW.glfwGetPrimaryMonitor(), 0, 0, width, height, 0);
        } else {
            GLFW.glfwSetWindowMonitor(window, 0, windowPosX[0], windowPosY[0], width, height, 0);
        }
    }
}
