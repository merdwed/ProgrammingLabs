package World;

import java.util.Objects;

public class Human extends Container implements WarmSource {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Human human = (Human) o;
        return hunger == human.hunger &&
                mood.equals(human.mood) &&
                interactionTransfer.equals(human.interactionTransfer) &&
                name.equals(human.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mood, interactionTransfer, name, hunger);
    }

    public Human() {
        this(new float[]{0, 0, 0});
    }

    public Human(float[] exDim) {
        this(exDim, "No name");
    }

    public Human(float[] exDim, String namearg) {
        super(exDim);
        this.name=namearg;
        this.hunger=DEFAULT_HUNGER;
        mood=new Mood();
        interactionTransfer = new InteractionTransfer();
        objectTemperatureController=new HumanTemperatureController();
        objectTemperatureController.needToUpdateObjects=content;
    }

    protected class HumanTemperatureController extends PhysicalObjectTemperatureController{
        @Override
        protected void updateThis(float temp){
            if(temp<COLD__TEMPERATURE) {
                System.out.println(Human.this.toString() + " feel cold");
                mood.someBadSituation();
            }
            super.updateThis(DEFAULT_HUMAN_TEMPERATURE);
        }
    }

    public class Mood{
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Mood mood = (Mood) o;
            return happinessRate == mood.happinessRate;
        }

        @Override
        public int hashCode() {
            return Objects.hash(happinessRate);
        }

        public Mood(){
            happinessRate=DEFAULT_HAPPY;
        }
        private final int DEFAULT_HAPPY = 10;
        private int happinessRate;
        public int getHappinessRate(){return happinessRate;}
        private void setHappinessRate(int happyArg){
            if(happinessRate > happyArg)
                System.out.print(Human.this.toString() + " has more upset.");
            if(happinessRate < happyArg)
                System.out.print(Human.this.toString() + " has more happy.");
            happinessRate=happyArg;
            System.out.println(" rate of happiness: " + happinessRate);
        }
        public void someBadSituation(){
            setHappinessRate(getHappinessRate() - 1);
        }
        public void someHappySituation(){

            setHappinessRate(getHappinessRate() + 1);
        }
    }
    public class InteractionTransfer{
        public boolean searchAndTake(Container container, Class classObject, SearchKey key){
            System.out.println(Human.this.toString() + " starts searching " +classObject.toString() + " in " + container.toString());
            PhysicalObject obj=container.findAndGet(classObject,key);
            if(obj == null)
                return false;
            Human.this.put(obj);
            return true;

        }
        public boolean putContentObjectTo( Class what, Container where, SearchKey key){
            System.out.println(Human.this.toString() + " try to put " + what.toString() + " to " + where.toString());
            PhysicalObject obj=Human.this.findAndGet(what,key);
            if( obj == null)
                return false;
            where.put(obj);
            return true;
        }
        public boolean putContentObjectToContentContainer( Class what, Class where, SearchKey whatKey, SearchKey whereKey){
            System.out.println(Human.this.toString() + " try to put " + what.toString() + " to " + where.toString());
            PhysicalObject whereObject=find(where, whereKey);
            PhysicalObject whatObject=findAndGet(what, whatKey);
            if(whereObject!= null && whatObject!=null && whereObject instanceof Container) {
                ((Container) whereObject).put(whatObject);
                return true;
            }
            return false;
        }
    }
    private final int DEFAULT_HUNGER = 65;
    private final float DEFAULT_HUMAN_TEMPERATURE=20f;
    private final float COLD__TEMPERATURE = 10f;
    public Mood mood;
    public InteractionTransfer interactionTransfer;
    private String name;
    private int hunger;

    @Override
    public boolean isActive(){
        return true;
    }
    @Override
    public float getSourceTemperature(){
        return DEFAULT_HUMAN_TEMPERATURE;
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
        PhysicalObject obj=find(dishClass);
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

                            mood.someHappySituation();
                        }
                        else
                            mood.someBadSituation();
                        content.remove(i);
                        break;
                    default:
                        mood.someBadSituation();
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


    @Override
    public String toString(){
        return name;
    }
}
