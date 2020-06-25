package tk.hipogriff.fallenkingdoms.engine;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

    // ID de la ventana
    private long window;

    // Datos de la ventana
    private String title;
    private int width, height;
    private boolean resizable;
    private boolean visible;

    public Window(String title, int width, int height, boolean resizable, boolean visible) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.resizable = resizable;
        this.visible = visible;
    }

    public void run() {
        System.out.println("Abriendo ventana " + title + " con LWGJL " + Version.getVersion() + "...");

        init();
        loop();

        // Libera las callbacks y destruye la ventana.
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Termina GLFW y libera las callbacks de error.
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init() {
        // Crea una callback de error. La implementación por defecto mostrará el mensaje de error en System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Inicializa GLFW. La mayoria de funciones de GLFW no funcionarán antes de ejecutar este método.
        if ( !glfwInit() ) throw new IllegalStateException("Unable to initialize GLFW");

        // Configuración de GLFW
        glfwDefaultWindowHints(); // opcional, las sugerencias de la ventana actual ya son las predeterminadas.
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // la ventana se mantendrá invisible después de la creación.
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // La ventana podrá cambiarse de tamaño. TODO: depende de "resizable"

        // Creación de la ventana
        window = glfwCreateWindow(300, 300, "Hello World!", NULL, NULL); //TODO: depende de width, height y title.
        if ( window == NULL ) throw new RuntimeException("Failed to create the GLFW window");

        // Crea una callback para el teclado. Se llamará cada vez que se presione, repita o suelte una tecla.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE ) glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });

        // Obtiene la pila de threads y empuja un nuevo frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Devuelve el tamaño de la ventana especificado en glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Devuelve la resolución del monitor primario
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Centra la ventana
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // el frame de la pila se saltará automáticamente

        // Hace el contexto actual de OpenGL
        glfwMakeContextCurrent(window);
        // Activa el v-sync
        glfwSwapInterval(1);

        // Hace la ventana visible
        glfwShowWindow(window); //TODO: depende de "visible"
    }

    private void loop() {
        /*
        Esta línea es crítica para la interoperación de LWJGL con el contexto OpenGL de GLFW, o cualquier contexto que
        se gestione externamente. LWJGL detecta el contexto actual en el subproceso actual, crea la instancia de
        GLCapabilities y hace que los enlaces OpenGL estén disponibles para su uso.
        */
        GL.createCapabilities();

        // Establece el color de limpiado,
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

        // Ejecuta el bucle de renderizado hasta que el usuario decida cerrar la ventan o pulse ESCAPE.
        while ( !glfwWindowShouldClose(window) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            glfwSwapBuffers(window); // swap the color buffers

            // Detección de eventos de ventana. La callback del teclado anterior solo se invocará durante esta llamada.
            glfwPollEvents();
        }
    }
}
