package World;

import World.PhysicalObject;

import java.util.Objects;

public abstract class Food extends PhysicalObject {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Food food = (Food) o;
        return shelfLife == food.shelfLife &&
                foodState == food.foodState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), shelfLife, foodState);
    }

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
        foodState=s;

        objectTimeController=new FoodTimeController();
        shelfLife=DEFAULT_SHELF_LIFE;
    }
    public class FoodTimeController extends PhysicalObjectTimeController{
        @Override
        protected void updateThis(){


            if(FoodTimeController.getGlobalTime() - createdTime > shelfLife){
                foodState=FoodState.SPOILED;
                System.out.println(Food.this.toString() + " is " + foodState.toString());
            }
        }
    }

    enum FoodState { COOKED, RAW, COAL, SPOILED}
    protected long shelfLife;
    protected FoodState foodState;
    private final long DEFAULT_SHELF_LIFE = WorldState.TimeController.FULL_DAY_LENGTH*365*10;

    @Override
    public String toString(){
        return "Food";
    }
}
