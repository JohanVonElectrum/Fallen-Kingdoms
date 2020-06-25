package FallenKingdoms.standard;

public class FileExtension {

    public static String SHADER = ".glsl";
    public static String VERTEX_SHADER = ".vertex" + SHADER;
    public static String FRAGMENT_SHADER = ".fragment" + SHADER;

    public static String getFilename(String path, String filename, String extension) {
        if (path.toCharArray()[path.length() - 1] == '/') path += "/";

        return path + filename + extension;
    }

}
