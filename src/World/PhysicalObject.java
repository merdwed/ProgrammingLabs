package World;

import Exceptions.NegativeDimensionsException;

import java.util.Arrays;
//import Exceptions.NegativeDimensionsException;

public abstract class PhysicalObject {

    public PhysicalObject() {
        this(new float[]{0, 0, 0});
    }

    public PhysicalObject(float[] exDim) {
        this(exDim, new float[]{0, 0, 0});
    }

    public PhysicalObject(float[] exDim, float[] inDim) {
        initDimensions();
        try {
            setExternalDimensions(exDim);
            setInternalDimensions(inDim);
        }
        catch(NegativeDimensionsException e){
            System.err.println(e.getMessage());
            System.out.println("object " + this.toString() + " cannot was created with negative dimensions. It was created with dimensions {0,0,0}");
        }
        objectTimeController=new PhysicalObjectTimeController();
        objectTimeController.needToUpdateObjects=null;
    }

    protected class PhysicalObjectTimeController extends WorldState.TimeController.ObjectTimeController{
        @Override
        protected void updateThis(){ }
    }


    protected PhysicalObjectTimeController objectTimeController;
    private float[] externalDimensions;
    private float[] internalDimensions;


    private void initDimensions() {
        externalDimensions = new float[]{0, 0, 0};
        internalDimensions = new float[]{0, 0, 0};
    }

    private boolean setExternalDimensions(float[] exDim) throws Exceptions.NegativeDimensionsException{
        if(exDim[0]<0 || exDim[1]<0 || exDim[2]<0)
            throw new Exceptions.NegativeDimensionsException("You wanna create object with negative external dimensions");
        if (Arrays.equals(externalDimensions, new float[]{0, 0, 0})) {
            for (int i = 0; i < exDim.length && i < 3; i++)
                this.externalDimensions[i] = exDim[i];
            return true;
        } else
            return false;

    }
    private boolean setInternalDimensions(float[] inDim) throws Exceptions.NegativeDimensionsException{
        if(inDim[0]<0 || inDim[1]<0 || inDim[2]<0)
            throw new Exceptions.NegativeDimensionsException("You wanna create object with negative internal dimensions");
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
