package World;


public class Room extends Container {
    public Room() {
        this(new float[]{0, 0, 0});
    }

    public Room(float[] exDim) {
        this(exDim, new float[]{0, 0, 0});
    }

    public Room(float[] exDim, float[] inDim) {
        super(exDim, inDim);
        objectTemperatureController=new RoomTemperatureController();
        objectTemperatureController.needToUpdateObjects=content;
    }

    protected class RoomTemperatureController extends PhysicalObject.PhysicalObjectTemperatureController{
        @Override
        protected void updateThis(float temp){
            WarmSource obj=(Furnace)find(Furnace.class,SearchKey.FIRST);
            if( obj != null && obj.isActive()){
                temp=(obj.getSourceTemperature());
            }
            super.updateThis(temp);
        }
    }
    public String toString(){

        return "Room";

    }
}
