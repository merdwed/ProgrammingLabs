package Taller;

import World.*;

public class Lab4Taller extends StoryTaller{
    final int numberOfEgg = 7;
    private static Lab4Taller ourInstance = new Lab4Taller();

    public static Lab4Taller getInstance() {
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
        actionRoom.put(new ChestBox(new float[]{2f,0.8f,0.5f},new float[]{1.95f,0.75f,0.45f}));
        actionRoom.put(new ChestBox(new float[]{1.8f,0.8f,0.5f},new float[]{1.75f,0.75f,0.45f}));
        actionRoom.put(furnace);
        for(int i=0;i<numberOfEgg;i++) {
            actionRoom.put(new Egg());
        }
        spruts.put(new Trousers());

        WorldState.WeatherController.objectTemperatureController.addToListOfUpdatableObjects(actionRoom);
        WorldState.TimeController.objectTimeController.addToListOfUpdatableObjects(actionRoom);

    }

    public void storyActions(){
        init();
        System.out.println("Aaaaand start");
        //1
        actionRoom.put(julio);
        actionRoom.put(spruts);

        //2
        spruts.interactionTransfer.searchAndTake(actionRoom,Chair.class,SearchKey.FIRST);
        spruts.interactionTransfer.searchAndTake(actionRoom,Chair.class,SearchKey.FIRST);
        spruts.breakContent();
        //3
        spruts.interactionTransfer.putContentObjectTo(FireWood.class,furnace, SearchKey.FIRST);
        spruts.interactionTransfer.putContentObjectTo(FireWood.class,furnace,SearchKey.FIRST);
        furnace.burnContent();
        //4
        spruts.interactionTransfer.searchAndTake(actionRoom,Egg.class,SearchKey.FIRST);
        spruts.interactionTransfer.searchAndTake(actionRoom,Egg.class,SearchKey.FIRST);
        spruts.breakContent();
        //5
        System.out.println("Spruts can put egg to pan, but");
        //6
        spruts.interactionTransfer.putContentObjectToContentContainer(Egg.class,Trousers.class, SearchKey.FIRST, SearchKey.FIRST);
        //7
        spruts.mood.someBadSituation();
        julio.mood.someBadSituation();
        //8
        while(julio.interactionTransfer.searchAndTake(spruts,Egg.class,SearchKey.FIRST)==true);
        while(julio.interactionTransfer.searchAndTake(actionRoom,Egg.class,SearchKey.FIRST)==true);
        //9
        System.out.println("Julio are starting gotovit");
        //10
        julio.interactionTransfer.searchAndTake(actionRoom,Pan.class,SearchKey.MAX);
        //11
        while(julio.interactionTransfer.putContentObjectToContentContainer(Egg.class, Pan.class,SearchKey.FIRST,SearchKey.FIRST)==true);
        julio.cookContent(Pan.class);
        //12
        {
            Chair tempChair = (Chair) actionRoom.findAndGet(Chair.class, SearchKey.FREE);
            tempChair.put(actionRoom.findAndGet(julio));
            actionRoom.put(tempChair);
            tempChair = (Chair) actionRoom.findAndGet(Chair.class, SearchKey.FREE);
            tempChair.put(actionRoom.findAndGet(spruts));
            actionRoom.put(tempChair);
        }
        //13
        {
            Pan tempPan=(Pan)julio.findAndGet(Pan.class,SearchKey.FIRST);
            while(julio.interactionTransfer.searchAndTake(tempPan,Egg.class,SearchKey.FIRST) && spruts.interactionTransfer.searchAndTake(tempPan,Egg.class,SearchKey.FIRST));
            actionRoom.put(tempPan);
            julio.eatContent();
            spruts.eatContent();
        }
        //test
        WorldState.TimeController.addToGlobalTime(13);
        WorldState.TimeController.addToGlobalTime(500);
        //let's sleep
        {
            ChestBox box=(ChestBox)actionRoom.findAndGet(ChestBox.class,SearchKey.MAX);
            box.put(spruts);
            box.closeCap();
            actionRoom.put(box);
            box=(ChestBox)actionRoom.findAndGet(ChestBox.class,SearchKey.FIRST);
            box.put(julio);
            actionRoom.put(box);
            WorldState.TimeController.setTimeState(WorldState.TimeController.TimeState.MIDNIGHT);
        }
        actionRoom.put(julio);

    }

}
