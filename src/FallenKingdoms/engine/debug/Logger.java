package FallenKingdoms.engine.debug;

public class Logger {

    private String parent;

    public Logger(String parent) {
        this.parent = parent;
    }

    public void info(String msg) {
        System.out.println(ConsoleColor.AQUA + msg);
    }

    public void warning(String msg) {
        System.out.println(ConsoleColor.GOLD + msg);
    }

    public void error(String msg) {
        System.err.println(ConsoleColor.RED + msg);
    }
}
