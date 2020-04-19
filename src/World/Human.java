package World;

public class Human extends Container {
    public Human() {
        this(new float[]{0, 0, 0});
    }

    public Human(float[] exDim) {
        this(exDim, "No name");
    }

    public Human(float[] exDim, String namearg) {
        super(exDim);
        this.name=namearg;
        this.hunger=defaultHunger;
        this.happinessRate = defaultHappy;
    }
    public final int defaultHunger = 65;
    public final int defaultHappy = 10;
    private String name;
    private int hunger;
    private int happinessRate;
    public boolean searchAndTake(Container container, Class classObject, SearchKey key){
        System.out.println(this.toString() + " starts searching " +classObject.toString() + " in " + container.toString());
        PhysicalObject obj=container.findAndGet(classObject,key);
        if(obj == null)
            return false;
        this.put(obj);
        return true;

    }
    public boolean putContentObjectTo( Class what, Container where, SearchKey key){
        System.out.println(this.toString() + " try to put " + what.toString() + " to " + where.toString());
        PhysicalObject obj=this.findAndGet(what,key);
        if( obj == null)
            return false;
        where.put(obj);
        return true;
    }
    public boolean putContentObjectToContentContainer( Class what, Class where, SearchKey whatKey, SearchKey whereKey){
        System.out.println(this.toString() + " try to put " + what.toString() + " to " + where.toString());
        PhysicalObject whereObject=find(where, whereKey);
        PhysicalObject whatObject=findAndGet(what, whatKey);
        if(whereObject!= null && whatObject!=null && whereObject instanceof Container) {
            ((Container) whereObject).put(whatObject);
            return true;
        }
        return false;
    }
    public boolean breakContent(){
        boolean b=false;
        for(int i=0;i<content.size();){
            if(content.get(i) instanceof Breaking) {
                b=true;
                System.out.print(this.toString() + " try to break some " +  content.get(i).toString() + " and get ");
                PhysicalObject temp = ((Breaking) content.get(i)).onBreak();
                if(temp==null) {
                    content.remove(i);
                    System.out.println("nothing");
                }
                else{
                    content.set(i,temp);
                    System.out.println(temp.toString());
                    i++;
                }
            }
            else
                i++;
        }
        return b;
    }
    public boolean cookContent(Class dishClass){
        PhysicalObject obj=find(dishClass,SearchKey.FIRST);
        if(obj instanceof Pan){
            return ((Pan) obj).fryContent();
        }
        return false;
    }
    public boolean eatContent(){
        boolean b=false;
        for(int i=0;i<content.size();){
            if(content.get(i) instanceof Food) {
                b=true;
                System.out.println(toString() + " eat some " + ((Food) content.get(i)).foodState.toString()+" " + content.get(i).toString());
                switch(((Food) content.get(i)).foodState){
                    case COOKED:
                        if(hunger>0) {
                            hunger -= 5;

                            someHappySituation();
                        }
                        else
                            someBadSituation();
                        content.remove(i);
                        break;
                    default:
                        someBadSituation();
                        content.remove(i);
                        break;

                }
                System.out.println(toString() + " is " + getHungerPercentRate() + "% hunger" );
            }
            else
                i++;
        }
        return b;
    }
    public int getHungerPercentRate(){
        return hunger;
    }
    public int getHappinessRate(){return happinessRate;}
    public void someBadSituation(){
        System.out.println(toString() + " has more upset");
        happinessRate -=1;
    }
    public void someHappySituation(){
        System.out.println(toString() + " has more happy");
        happinessRate +=1;
    }

    @Override
    public String toString(){
        return name;
    }
}
