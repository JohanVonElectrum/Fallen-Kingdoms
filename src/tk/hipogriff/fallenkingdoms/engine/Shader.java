package tk.hipogriff.fallenkingdoms.engine;

import org.lwjgl.BufferUtils;
import tk.hipogriff.fallenkingdoms.engine.debug.Logger;
import tk.hipogriff.fallenkingdoms.engine.types.Color;
import tk.hipogriff.fallenkingdoms.math.Matrix4x4;
import tk.hipogriff.fallenkingdoms.standard.FileExtension;
import tk.hipogriff.fallenkingdoms.standard.Folder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;

public class Shader {

    private static Logger logger = new Logger("Shader");

    private int programID;
    private int vertexShader;
    private int fragmentShader;

    public Shader(String filename) {
        programID = glCreateProgram();

        vertexShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShader, CreateShader(filename + FileExtension.VERTEX_SHADER));
        glCompileShader(vertexShader);
        if (glGetShaderi(vertexShader, GL_COMPILE_STATUS) != GL_TRUE) {
            logger.error("No se puede compilar el shader de vertices.\n" + glGetProgramInfoLog(vertexShader));
            System.exit(1);
        }

        fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShader, CreateShader(filename + FileExtension.FRAGMENT_SHADER));
        glCompileShader(fragmentShader);
        if (glGetShaderi(fragmentShader, GL_COMPILE_STATUS) != GL_TRUE) {
            logger.error("No se puede compilar el shader de fragmentos.\n" + glGetProgramInfoLog(fragmentShader));
            System.exit(1);
        }

        glAttachShader(programID, vertexShader);
        glAttachShader(programID, fragmentShader);

        glBindAttribLocation(programID, 0, "vertices");
        glBindAttribLocation(programID, 1, "uvs");

        glLinkProgram(programID);
        if (glGetProgrami(programID, GL_LINK_STATUS) != GL_TRUE) {
            logger.error("No se puede enlazar el programa.\n" + glGetProgramInfoLog(programID));
            System.exit(1);
        }
        glValidateProgram(programID);
        if (glGetProgrami(programID, GL_VALIDATE_STATUS) != GL_TRUE) {
            logger.error("No se puede validar el programa.\n" + glGetProgramInfoLog(programID));
            System.exit(1);
        }
    }

    private String CreateShader(String filename) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(new File(Folder.SHADERS, filename)));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void setUniform(String id, Color color) {
        int location = glGetUniformLocation(programID, id);
        if (location != GL_TRUE) glUniform4f(location, color.getR(), color.getG(), color.getB(), color.getA());
    }

    public void setUniform(String id, Matrix4x4 matrix) {
        int location = glGetUniformLocation(programID, id);
        FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
        matrix.getBuffer(buffer);
        if (location != GL_TRUE) glUniformMatrix4fv(location, false, buffer); buffer.flip();
    }

    public void bind() {
        glUseProgram(programID);
    }

    public void unbind() {
        glUseProgram(0);
    }

}
