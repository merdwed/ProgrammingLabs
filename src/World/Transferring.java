package World;

public interface Transferring{
    boolean put(PhysicalObject item);
    PhysicalObject findAndGet(Class classObject, SearchKey key);
    //World.PhysicalObject findAndGetRec(Class classObject, World.SearchKey key, int depthRec);
}