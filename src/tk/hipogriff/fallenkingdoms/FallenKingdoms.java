package tk.hipogriff.fallenkingdoms;

import tk.hipogriff.fallenkingdoms.engine.Window;

public class FallenKingdoms {

    /*
    Clase principal del juego para la inicialización.
     */

    public static void main(String[] args) {
        Window window = new Window("Fallen Kingdoms", 800, 600, false, true);
        window.run();
    }

}
