package World;

import World.PhysicalObject;

public abstract class Food extends PhysicalObject {
    public Food() {
        this(new float[]{0, 0, 0});
    }

    public Food(float[] exDim) {
        this(exDim, new float[]{0, 0, 0});
    }
    public Food(float[] exDim, FoodState s) {
        this(exDim, new float[]{0, 0, 0},s);
    }
    public Food(float[] exDim, float[] inDim) {
        this(exDim, inDim,FoodState.RAW);
    }
    public Food(float[] exDim, float[] inDim, FoodState s) {
        super(exDim, inDim);
        state=s;
    }
    enum FoodState{ COOKED, RAW, COAL, SPOILED}
    protected FoodState state;
    @Override
    public String toString(){
        return "Food";
    }
}
