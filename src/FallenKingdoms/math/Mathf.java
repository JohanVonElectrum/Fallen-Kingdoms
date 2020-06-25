package FallenKingdoms.math;

public class Mathf {

    public static float clamp(float value, float min, float max) {
        if (value < min) return min;
        else if (value > max) return max;
        else return value;
    }

    public static int mod(int a, int b) {
        b = Math.abs(b);
        while (a < 0 || a >= b) a -= b * (int) Math.signum(a);
        return a;
    }

}
