package World;

import World.Container;

public class Room extends Container {
    public Room() {
        this(new float[]{0, 0, 0});
    }

    public Room(float[] exDim) {
        this(exDim, new float[]{0, 0, 0});
    }

    public Room(float[] exDim, float[] inDim) {
        super(exDim, inDim);
    }

    public String toString(){

        return "Room";

    }
}
