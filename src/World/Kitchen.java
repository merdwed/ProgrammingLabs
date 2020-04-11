package World;

public class Kitchen extends Room {
    public Kitchen() {
        this(new float[]{0, 0, 0});
    }

    public Kitchen(float[] inDim) {
        this(inDim, new float[]{0, 0, 0});
    }

    public Kitchen(float[] inDim, float[] exDim) {
        super(inDim, exDim);
    }

    public String toString(){

        return "Kitchen";

    }
}
