package tk.hipogriff.fallenkingdoms.math;

public class Mathf {

    public static float clamp(float value, float min, float max) {
        if (value < min) return min;
        else if (value > max) return max;
        else return value;
    }

    public static Vector clamp(Vector value, float min, float max) {
        Vector output = value.clone();
        for (int i = 0; i < output.getDimension(); i++) {
            output.setComponent(clamp(output.getComponent(i), min, max), i);
        }
        return output;
    }

}
