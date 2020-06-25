package FallenKingdoms.engine.graphics;

import FallenKingdoms.standard.Folder;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Material {

    private String path;
    private Texture texture;
    private float width, height;
    private int textureID;

    public Material(String path) {
        this.path = path;
    }

    public void create() {
        try {
            FileInputStream inputStream = new FileInputStream(new File(path));
            texture = TextureLoader.getTexture(path.split("[.]")[1], inputStream, GL11.GL_NEAREST);
            width = texture.getWidth();
            height = texture.getHeight();
            textureID = texture.getTextureID();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        GL13.glDeleteTextures(textureID);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public int getTextureID() {
        return textureID;
    }
}
