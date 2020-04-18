package World;

import java.util.Arrays;


public abstract class PhysicalObject {

    public PhysicalObject() {
        this(new float[]{0, 0, 0});
    }

    public PhysicalObject(float[] exDim) {
        this(exDim, new float[]{0, 0, 0});
    }

    public PhysicalObject(float[] exDim, float[] inDim) {
        initDimensions();
        setExternalDimensions(exDim);
        setInternalDimensions(inDim);
        objectTimeController=new PhysicalObjectTimeController();
        objectTimeController.needToUpdateObjects=null;
    }
    protected class PhysicalObjectTimeController extends WorldState.TimeController.ObjectTimeController{
        @Override
        protected void updateThis(){
            System.out.println("update in PhysicalObject " + PhysicalObject.this.toString());
        }
    }


    protected PhysicalObjectTimeController objectTimeController;
    private float[] externalDimensions;
    private float[] internalDimensions;


    private void initDimensions() {
        externalDimensions = new float[]{0, 0, 0};
        internalDimensions = new float[]{0, 0, 0};
    }

    private boolean setExternalDimensions(float[] exDim) {
        if (Arrays.equals(externalDimensions, new float[]{0, 0, 0})) {
            for (int i = 0; i < exDim.length && i < 3; i++)
                this.externalDimensions[i] = exDim[i];
            return true;
        } else
            return false;

    }

    private boolean setInternalDimensions(float[] inDim) {
        if (Arrays.equals(internalDimensions, new float[]{0, 0, 0})) {
            for (int i = 0; i < inDim.length && i < 3; i++)
                this.internalDimensions[i] = inDim[i];
            return true;
        } else
            return false;
    }
    public float[] getExternalDimensions()
    {
        return externalDimensions;
    }
    public float[] getInternalDimensions()
    {
        return internalDimensions;
    }

    public float sizeCriterion(){
        return externalDimensions[0]*externalDimensions[1]*externalDimensions[2];
    }
    @Override
    public String toString() {
        return "PhysicalObject";
    }
}
