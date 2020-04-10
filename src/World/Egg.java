package World;

public class Egg extends Food implements Frying, Breaking {
    public Egg(){
        this(new float[] {0,0,0});
    }
    public Egg(float[] exDim) {
        this(exDim, Food.FoodState.RAW);

    }
    public Egg(float[] exDim, Food.FoodState s){
        this(exDim, s, false);
    }
    public Egg(float[] exDim, Food.FoodState s, boolean brokeState)
    {
        super(exDim);
        state=s;
        shellBroken=brokeState;
    }
    private boolean shellBroken;
    public boolean isShellBroken() {
        return shellBroken;
    }
    public PhysicalObject onFry(){
        if(state.equals(Food.FoodState.RAW))
            return new Egg(getInternalDimensions(),Food.FoodState.COOKED);
        if(state.equals(Food.FoodState.COOKED))
            return new Egg(getInternalDimensions(),Food.FoodState.COAL);
        return this;
    }
    public PhysicalObject onBreak(){
        if(isShellBroken() == false){
            Egg newEgg= new Egg(getExternalDimensions(),state, true);
            return newEgg;
        }
        return this;
    }
    @Override
    public String toString(){
        return "World.Egg";
    }
}
