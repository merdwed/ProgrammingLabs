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
    }

    protected class RoomTemperatureController extends PhysicalObject.PhysicalObjectTemperatureController{
        RoomTemperatureController(){
            needToUpdateObjects=content;
        }
        @Override
        protected void updateThis(float temp){
            WarmSource obj=(Furnace)find(Furnace.class);
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
