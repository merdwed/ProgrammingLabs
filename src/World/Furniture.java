package World;

public class Furniture extends Container implements Burning {

    public Furniture() {
        this(new float[]{0, 0, 0});
    }

    public Furniture(float[] inDim) {
        this(inDim, new float[]{0, 0, 0});
    }

    public Furniture(float[] inDim, float[] exDim) {
        super(inDim, exDim);
    }
    @Override
    public String toString(){
        return "World.Furniture";
    }
}
