package World;

public class FireWood extends PhysicalObject implements Burning{
    public FireWood() {
        this(new float[]{0, 0, 0});
    }

    public FireWood(float[] exDim) {
        this(exDim, new float[]{0, 0, 0});
    }

    public FireWood(float[] exDim, float[] inDim) {
        super(exDim, inDim);
    }
    @Override
    public String toString(){
        return "FireWood";
    }
}
