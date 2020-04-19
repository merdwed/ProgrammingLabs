package World;

import java.util.ArrayList;

public class ChestBox extends Furniture {
    public ChestBox() {
        this(new float[]{0, 0, 0});
    }

    public ChestBox(float[] exDim) {
        this(exDim, new float[]{0, 0, 0});


    }

    public ChestBox(float[] exDim, float[] inDim) {
        super(exDim, inDim);
        objectTemperatureController=new ChestBoxTemperatureController();
        objectTemperatureController.needToUpdateObjects=content;

    }

    private boolean closingState =false;
    public void closeCap(){closingState=true;}
    public void openCap(){closingState=false;}
    protected class ChestBoxTemperatureController extends PhysicalObjectTemperatureController{
        @Override
        protected void updateThis(float temp){
            WarmSource obj=(Human)find(Human.class);
            if( closingState == true && obj != null && obj.isActive()){
                temp=(obj.getSourceTemperature());
            }
            super.updateThis(temp);
        }
    }
    @Override
    public float sizeCriterion(){
        return getInternalDimensions()[0]*getInternalDimensions()[1]*getInternalDimensions()[2];
    }
    @Override
    public String toString(){
        return "ChestBox";
    }

}
