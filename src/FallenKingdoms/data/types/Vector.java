package FallenKingdoms.data.types;

public class Vector {

    int dim;
    float[] components;

    public Vector(float... components) {
        this.dim = components.length;
        this.components = components;
    }

    public float[] getArr() {
        return components;
    }

    public Vector add(Vector other) throws Exception {
        if (other.dim != this.dim) throw new Exception("Both vectors must be the same dimension.");

        Vector add = new Vector(components.clone());

        for (int i = 0; i < dim; i++) {
            add.components[i] += other.components[i];
        }

        return add;
    }

    public Vector multiply(float multiplier) {
        Vector multiply = new Vector(components.clone());

        for (int i = 0; i < dim; i++) {
            multiply.components[i] *= multiplier;
        }

        return multiply;
    }

    public float scaProd(Vector other) throws Exception {
        if (other.dim != this.dim) throw new Exception("Both vectors must be the same dimension.");

        float prod = 0;

        for (int i = 0; i < dim; i++) {
            prod += components[i] * other.components[i];
        }

        return prod;
    }

    public Vector vecProd(Vector other) throws Exception {
        if (other.dim != this.dim) throw new Exception("Both vectors must be the same dimension.");

        Vector prod = new Vector(components);

        for (int i = 0; i < dim; i++) {
            prod.components[i] *= other.components[i];
        }

        return prod;
    }

    public Vector3f to3f() {
        return new Vector3f(components[0], components[1], components[2]);
    }

    public String toString() {
        String str = "(";
        for (int i = 0; i < dim; i++) {
            str += (str == "(" ? components[i] : ", " + components[i]);
        }
        return str + ")";
    }

}
