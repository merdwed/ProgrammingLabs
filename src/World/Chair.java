package World;

public class Chair extends Furniture implements Breaking {
    public Chair() {
        this(new float[]{0, 0, 0});
    }

    public Chair(float[] exDim) {
        this(exDim, new float[]{0, 0, 0});
    }

    public Chair(float[] exDim, float[] inDim) {
        super(exDim,inDim);
    }
    public PhysicalObject onBreak(){
        return new FireWood(new float[]{getExternalDimensions()[0]/3,getExternalDimensions()[1]/3,getExternalDimensions()[2]/3});

    }
    @Override
    public String toString(){
        return "Chair";
    }
}
