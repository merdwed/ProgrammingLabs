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
    public Egg(float[] exDim, Food.FoodState s, boolean brokeState) {
        super(exDim);
        foodState=s;
        shellBroken=brokeState;
        shelfLife=DEFAULT_SHELF_LIFE;
    }
    public Egg(float[] exDim, Food.FoodState s, boolean brokeState, int shelfLifeP) {
        super(exDim);
        foodState=s;
        shellBroken=brokeState;
        shelfLife=shelfLifeP;
    }
    private final int DEFAULT_SHELF_LIFE = 1440*90;
    private final int COOKED_SHELF_LIFE = 1440*3;
    private boolean shellBroken;
    public boolean isShellBroken() {
        return shellBroken;
    }
    public PhysicalObject onFry(){
        if(foodState.equals(Food.FoodState.RAW))
            return new Egg(getInternalDimensions(),Food.FoodState.COOKED,true,COOKED_SHELF_LIFE);
        if(foodState.equals(Food.FoodState.COOKED))
            return new Egg(getInternalDimensions(),Food.FoodState.COAL,true,1440*365*300);
        return this;
    }
    public PhysicalObject onBreak(){
        if(isShellBroken() == false){
            return new Egg(getExternalDimensions(),foodState, true);
        }
        return this;
    }
    @Override
    public String toString(){
        return "Egg";
    }
}
