package World;

public class Pan extends Container {
    public Pan() {
        this(new float[]{0, 0, 0});
    }

    public Pan(float[] exDim) {
        this(exDim, new float[]{0, 0, 0});
    }

    public Pan(float[] exDim, float[] inDim) {
        super(exDim, inDim);
    }
    public boolean fryContent(){
        boolean b=false;
        for(int i=0;i<content.size();){
            if(content.get(i) instanceof Frying) {
                b=true;
                System.out.print(this.toString() + " try to cook some " +  content.get(i).toString() + " and get ");
                PhysicalObject temp = ((Frying) content.get(i)).onFry();
                if(temp==null) {
                    content.remove(i);
                    System.out.println("nothing");
                }
                else {
                    content.set(i, temp);
                    if(temp instanceof  Food)
                       System.out.println(((Food)temp).state.toString());
                    i++;
                }
            }
            else
                i++;
        }
        return b;
    }
    @Override
    public String toString(){
        return "World.Pan";
    }
}
