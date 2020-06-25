package tk.hipogriff.fallenkingdoms.engine.types;

import static tk.hipogriff.fallenkingdoms.math.Mathf.*;

import tk.hipogriff.fallenkingdoms.error.DimensionSizeException;
import tk.hipogriff.fallenkingdoms.math.Vector;

public class Color extends Vector {

    public Color(float... rgba) {
        if (rgba == null || rgba.length == 0) rgba = new float[] { 0, 0, 0, 0 };

        if (rgba.length == 3) rgba = new float[] { rgba[0], rgba[1], rgba[2], 1 };
        if (rgba.length != 4) throw new DimensionSizeException("Color must have 4 dimensions.");

        this.dimension = rgba.length;
        this.components = rgba;

        clamp(this, 0, 1);
    }

    public float getR() {
        return getComponent(0);
    }

    public float getG() {
        return getComponent(1);
    }

    public float getB() {
        return getComponent(2);
    }

    public float getA() {
        return getComponent(3);
    }

}
