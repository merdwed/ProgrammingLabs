package World;

import World.PhysicalObject;

public interface Burning {
    default PhysicalObject onBurn(){
        return null;
    }
}
