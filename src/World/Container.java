package World;

import java.util.ArrayList;

public abstract class Container extends PhysicalObject implements Transferring {
    //constructors
    public Container() {
        this(new float[]{0, 0, 0});
    }

    public Container(float[] exDim) {
        this(exDim, new float[]{0, 0, 0});


    }

    public Container(float[] exDim, float[] inDim) {
        super(exDim, inDim);
        content = new ArrayList<PhysicalObject>();
    }
    //final static int defaultDepth = 5;
    //field
    protected ArrayList<PhysicalObject> content;

    //methods
    public boolean put(PhysicalObject item) {
        content.add(item);
        System.out.println("Some " + item.toString() + " was put to " + this.toString());
        return true;
    }
    private int keyChecker(PhysicalObject current, PhysicalObject check, SearchKey key){
        //return 0 - nothing, return 1 - object found go return, 2- object found, but no return now

        switch (key) {
            case RANDOM:
            case FIRST:
                return 1;

            case FREE:
                if(check instanceof  Container && ((Container) check).content.isEmpty())
                    return 1;
                break;
            case MAX:
                if (current == null || current.sizeCriterion() > check.sizeCriterion())
                    return 2;
                break;
            case MIN:
                if (current == null || current.sizeCriterion() < check.sizeCriterion())
                    return 2;
                break;
        }
        return 0;
    }
    protected PhysicalObject find(Class classObject, SearchKey key){
        PhysicalObject returnObject=null;
        int forKeyChecker=0;
        for (int i = 0; i < content.size(); i++) {
            if(content.get(i).getClass().equals(classObject)) {
                forKeyChecker=keyChecker(returnObject,content.get(i),key);
                switch(forKeyChecker){
                    case 0:
                        break;
                    case 1:
                        returnObject = content.get(i);
                        return returnObject;
                    case 2:
                        returnObject = content.get(i);;
                        break;
                }
            }
        }
        return returnObject;
    }
    protected PhysicalObject find(PhysicalObject pObject){
        int forKeyChecker=0;
        for (int i = 0; i < content.size(); i++) {
            if(content.get(i)==pObject)
                return pObject;
        }
        return null;
    }
    public PhysicalObject findAndGet(PhysicalObject pObject) {
        PhysicalObject returnObject=find(pObject);
        if(returnObject!=null)
            content.remove(pObject);
        return returnObject;
    }
    public PhysicalObject findAndGet(Class classObject, SearchKey key) {
        PhysicalObject returnObject=find(classObject, key);
        if(returnObject!=null)
            content.remove(returnObject);
        return returnObject;
    }

    /*public World.PhysicalObject findAndGetRec(Class classObject, SearchKey key, int depth) {
        World.PhysicalObject returnObject=findAndGet(classObject, key);
        if(depth==0)
            return returnObject;
        World.PhysicalObject tempObject = null;
        for(int i = 0; i < content.size(); i++) {
            if(content.get(i) instanceof World.Container) {
                tempObject = ((World.Container) content.get(i)).findAndGetRec(classObject, key, depth-1);
                if(tempObject == null) continue;
                switch(key){
                    case RANDOM:
                    case FIRST:
                        return tempObject;
                    case MAX:
                        if(returnObject == null || returnObject.sizeCriterion() < tempObject.sizeCriterion())
                            returnObject=tempObject;
                        break;
                    case MIN:
                        if(returnObject == null || returnObject.sizeCriterion() > tempObject.sizeCriterion())
                            returnObject=tempObject;
                        break;
                }
            }
        }
        return returnObject;
    }*/
    @Override
    public String toString(){
        return "World.Container";
    }
}
