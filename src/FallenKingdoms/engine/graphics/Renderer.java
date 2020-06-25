package FallenKingdoms.engine.graphics;

import FallenKingdoms.data.types.Matrix4f;
import FallenKingdoms.engine.io.Window;
import FallenKingdoms.engine.objects.Camera;
import FallenKingdoms.engine.objects.GameObject;
import FallenKingdoms.engine.objects.Transform;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

public class Renderer {

    private Window window;
    private Shader shader;

    public Renderer(Window window, Shader shader) {
        this.window = window;
        this.shader = shader;
    }

    public void renderObject(GameObject gameObject, Camera camera) {
        GL30.glBindVertexArray(gameObject.getMesh().getVAO());
        GL30.glEnableVertexAttribArray(0);
        GL30.glEnableVertexAttribArray(1);
        GL30.glEnableVertexAttribArray(2);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, gameObject.getMesh().getIBO());
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL13.glBindTexture(GL11.GL_TEXTURE_2D, gameObject.getMesh().getMaterial().getTextureID());
        shader.bind();
        shader.setUniform("projection", window.getProjectionMatrix());
        shader.setUniform("view", camera.getTransform().getViewMatrix());
        shader.setUniform("transform", gameObject.getTransform().getTransformMatrix());
        shader.setUniform("scale", 100f);
        GL11.glDrawElements(GL11.GL_TRIANGLES, gameObject.getMesh().getIndices().length, GL11.GL_UNSIGNED_INT, 0);
        shader.unbind();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL30.glDisableVertexAttribArray(0);
        GL30.glDisableVertexAttribArray(1);
        GL30.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

}
