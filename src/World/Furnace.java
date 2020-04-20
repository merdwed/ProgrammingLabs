package World;

import java.util.Objects;

public class Furnace extends Furniture implements WarmSource{
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Furnace furnace = (Furnace) o;
        return startingBurnTime == furnace.startingBurnTime &&
                warmDuration == furnace.warmDuration &&
                active == furnace.active;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), startingBurnTime, warmDuration, active);
    }

    public Furnace() {
        this(new float[]{0, 0, 0});
    }

    public Furnace(float[] inDim) {
        this(inDim, new float[]{0, 0, 0});
    }

    public Furnace(float[] inDim, float[] exDim) {
        super(inDim, exDim);
        objectTimeController=new FurnaceTimeController();
    }
    private long startingBurnTime = 0;
    private long warmDuration=0;
    private boolean active =false;
    final float TIME_OF_BURNING_COEFFICIENT = 10000f;
    final float DEFAULT_FURNACE_ACTIVE_TEMPERATURE = 20f;
    protected class FurnaceTimeController extends PhysicalObjectTimeController{
        @Override
        protected void updateThis(){
            if(getGlobalTime()-startingBurnTime > warmDuration){
                System.out.println(Furnace.this.toString() + " end make warm");
                warmDuration=0;
                active=false;
            }
        }
    }
    @Override
    public boolean isActive(){
        return active;
    }
    @Override
    public float getSourceTemperature(){
        return DEFAULT_FURNACE_ACTIVE_TEMPERATURE;
    }
    public boolean burnContent(){
        boolean b=false;
        for(int i=0;i<content.size();){
            if(content.get(i) instanceof Burning) {
                PhysicalObject burnObject=content.get(i);
                b = true;
                PhysicalObject temp = ((Burning)burnObject).onBurn();
                System.out.print(this.toString() + " try to burn some " + burnObject.toString() + " and get ");
                if (temp == null) {
                    content.remove(i);
                    System.out.println("nothing");
                }
                else {
                    content.set(i, temp);
                    System.out.println(temp.toString());
                    i++;
                }
                float[] f=burnObject.getExternalDimensions();
                if(warmDuration==0){
                    startingBurnTime=FurnaceTimeController.getGlobalTime();
                    active =true;
                }
                warmDuration+=(int)(f[0]*f[1]*f[2]*TIME_OF_BURNING_COEFFICIENT);
                System.out.println(this.toString() + " will make warm for " + warmDuration + " tics");
            }
            else
                i++;
        }
        return b;
    }
    @Override
    public String toString(){
        return "Furnace";
    }
}
