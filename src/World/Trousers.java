package World;

import World.Container;

public class Trousers extends Container {
    public Trousers() {
        this(new float[]{0, 0, 0});
    }

    public Trousers(float[] exDim) {
        this(exDim, new float[]{0, 0, 0});
    }

    public Trousers(float[] exDim, float[] inDim) {
        super(exDim, inDim);
    }
    @Override
    public String toString(){
        return "Trousers";
    }
}
