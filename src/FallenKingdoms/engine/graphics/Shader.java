package FallenKingdoms.engine.graphics;

import FallenKingdoms.data.types.Vector2f;
import FallenKingdoms.data.types.Vector3f;
import FallenKingdoms.data.types.Matrix4f;
import FallenKingdoms.engine.debug.Logger;
import FallenKingdoms.engine.utils.FileUtils;
import FallenKingdoms.standard.FileExtension;
import FallenKingdoms.standard.Folder;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

public class Shader {

    private String vertexFile, fragmentFile;
    private int vertexID, fragmentID, programID;

    private Logger logger = new Logger("Shader");

    public Shader(String shadername) {
        String vertexPath = Folder.SHADERS + shadername + FileExtension.VERTEX_SHADER;
        String fragmentPath = Folder.SHADERS + shadername + FileExtension.FRAGMENT_SHADER;

        logger.info("Loading " + vertexPath + "...");
        logger.info("Loading " + fragmentPath + "...");

        this.vertexFile = FileUtils.loadAsString(vertexPath);
        this.fragmentFile = FileUtils.loadAsString(fragmentPath);
    }

    public void create() {
        programID = GL20.glCreateProgram();

        vertexID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        GL20.glShaderSource(vertexID, vertexFile);
        GL20.glCompileShader(vertexID);
        if (GL20.glGetShaderi(vertexID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            System.err.println("ERROR: Vertex Shader: " + GL20.glGetShaderInfoLog(vertexID));
            return;
        }

        fragmentID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        GL20.glShaderSource(fragmentID, fragmentFile);
        GL20.glCompileShader(fragmentID);
        if (GL20.glGetShaderi(fragmentID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            System.err.println("ERROR: Fragment Shader: " + GL20.glGetShaderInfoLog(fragmentID));
            return;
        }

        GL20.glAttachShader(programID, vertexID);
        GL20.glAttachShader(programID, fragmentID);

        GL20.glLinkProgram(programID);
        if (GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
            System.err.println("ERROR: Program linking: " + GL20.glGetProgramInfoLog(programID));
            return;
        }

        GL20.glValidateProgram(programID);
        if (GL20.glGetProgrami(programID, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE) {
            System.err.println("ERROR: Program validating: " + GL20.glGetProgramInfoLog(programID));
            return;
        }
    }

    public int getUniformLocation(String name) {
        return GL20.glGetUniformLocation(programID, name);
    }

    public void setUniform(String name, float value) {
        GL20.glUniform1f(getUniformLocation(name), value);
    }

    public void setUniform(String name, int value) {
        GL20.glUniform1i(getUniformLocation(name), value);
    }

    public void setUniform(String name, boolean value) {
        GL20.glUniform1i(getUniformLocation(name), value ? 1 : 0);
    }

    public void setUniform(String name, Vector2f value) {
        GL20.glUniform2f(getUniformLocation(name), value.getX(), value.getY());
    }

    public void setUniform(String name, Vector3f value) {
        GL20.glUniform3f(getUniformLocation(name), value.getX(), value.getY(), value.getZ());
    }

    public void setUniform(String name, Matrix4f value) {
        FloatBuffer matrix = MemoryUtil.memAllocFloat(Matrix4f.SIZE * Matrix4f.SIZE);
        matrix.put(value.getAll()).flip();
        GL20.glUniformMatrix4fv(getUniformLocation(name), true, matrix);
    }

    public void bind() {
        GL20.glUseProgram(programID);
    }

    public void unbind() {
        GL20.glUseProgram(0);
    }

    public void destroy() {
        GL20.glDetachShader(programID, vertexID);
        GL20.glDetachShader(programID, fragmentID);
        GL20.glDeleteShader(vertexID);
        GL20.glDeleteShader(fragmentID);
        GL20.glDeleteProgram(programID);
    }

}
