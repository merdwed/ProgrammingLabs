package Taller;

import World.*;

public class Lab3Taller extends StoryTaller{
    private static Lab3Taller ourInstance = new Lab3Taller();

    public static Lab3Taller getInstance() {
        return ourInstance;
    }
    static Room actionRoom = new Kitchen(new float[]{5f,4f,3f});
    static Human spruts = new Human(new float[]{1.8f,0.7f,0.4f}, "Spruts");
    static Human julio = new Human(new float[]{1.67f,0.6f,0.3f}, "Julio");
    static Furnace furnace = new Furnace(new float[]{1f,0.5f,0.5f}, new float[]{0.6f,0.3f,0.3f});

    private void init() {
        actionRoom.put(new Chair(new float[]{0.8f,0.5f,0.5f}));
        actionRoom.put(new Chair(new float[]{0.8f,0.5f,0.5f}));
        actionRoom.put(new Chair(new float[]{0.8f,0.5f,0.5f}));
        actionRoom.put(new Chair(new float[]{0.8f,0.5f,0.5f}));
        actionRoom.put(new Pan(new float[]{0.3f,0.3f,0.1f}));
        actionRoom.put(new Pan(new float[]{0.4f,0.35f,0.2f}));
        for(int i=0;i<20;i++) {
            actionRoom.put(new Egg());
        }
        spruts.put(new Trousers());

    }

    public void storyActions(){
        init();
        System.out.println("Aaaaand start");
        //1
        actionRoom.put(julio);
        actionRoom.put(spruts);
        //2
        spruts.searchAndTake(actionRoom,Chair.class,SearchKey.FIRST);
        spruts.searchAndTake(actionRoom,Chair.class,SearchKey.FIRST);
        spruts.breakContent();
        //3
        spruts.putContentObjectTo(furnace, FireWood.class,SearchKey.FIRST);
        spruts.putContentObjectTo(furnace,FireWood.class,SearchKey.FIRST);
        furnace.burnContent();
        //4
        spruts.searchAndTake(actionRoom,Egg.class,SearchKey.FIRST);
        spruts.searchAndTake(actionRoom,Egg.class,SearchKey.FIRST);
        spruts.breakContent();
        //5
        System.out.println("Spruts can put egg to pan, but");
        //6
        spruts.putContentObjectToContentContainer(Egg.class,Trousers.class, SearchKey.FIRST, SearchKey.FIRST);
        //7
        System.out.println("Julio is upset");
        //8
        while(julio.searchAndTake(spruts,Egg.class,SearchKey.FIRST)==true);
        while(julio.searchAndTake(actionRoom,Egg.class,SearchKey.FIRST)==true);
        //9
        System.out.println("Julio are starting gotovit");
        //10
        julio.searchAndTake(actionRoom,Pan.class,SearchKey.MAX);
        //11
        while(julio.putContentObjectToContentContainer(Egg.class, Pan.class,SearchKey.FIRST,SearchKey.FIRST)==true);
        julio.cookContent(Pan.class);
        //12
        Chair tempChair=(Chair)actionRoom.findAndGet(Chair.class,SearchKey.FREE);
        tempChair.put(actionRoom.findAndGet(julio));
        actionRoom.put(tempChair);
        tempChair=(Chair)actionRoom.findAndGet(Chair.class, SearchKey.FREE);
        tempChair.put(actionRoom.findAndGet(spruts));
        actionRoom.put(tempChair);

    }
}
