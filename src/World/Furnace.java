package World;

public class Furnace extends Furniture{
    public Furnace() {
        this(new float[]{0, 0, 0});
    }

    public Furnace(float[] inDim) {
        this(inDim, new float[]{0, 0, 0});
    }

    public Furnace(float[] inDim, float[] exDim) {
        super(inDim, exDim);
    }
    public boolean burnContent(){
        boolean b=false;
        for(int i=0;i<content.size();){
            if(content.get(i) instanceof Burning) {
                b = true;
                PhysicalObject temp = ((Burning) content.get(i)).onBurn();
                System.out.print(this.toString() + " try to burn some " + content.get(i).toString() + " and get ");
                if (temp == null) {
                    content.remove(i);
                    System.out.println("nothing");
                }
                else {
                    content.set(i, temp);
                    System.out.println(temp.toString());
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
        return "Furnace";
    }
}
