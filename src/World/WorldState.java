package World;

import java.util.ArrayList;

public class WorldState {

    public static class WeatherController {

        final static float DEFAULT_TEMPERATURE = 15f;
        private WeatherController(){
            temperature = DEFAULT_TEMPERATURE;
        }
        private static float temperature;

        public static float getTemperature() {
            return temperature;
        }
        public static void updateWeather(){
            temperature=temperatureVersusTime(TimeController.ObjectTimeController.getDayTime());
        }
        private static float temperatureVersusTime(long time){
            return (- time*time*time/1600 +  time*time - 150 *time)/17280-3;//this mathematical function imitate temperature versus time for 0<=t<1440
        }
    }

    public static class TimeController{

        static{
            dayTime=0;
            globalTime=0;
            timeState=TimeState.MIDNIGHT;
        }

        public enum TimeState{
            MIDNIGHT,
            NIGHT,
            MORNING,
            MIDDAY,
            DAY,
            EVENING
        }
        public static class ObjectTimeController{
            ObjectTimeController(){
                createdTime=getGlobalTime();
                needToUpdateObjects=new ArrayList<PhysicalObject>();
            }
            protected long createdTime;
            protected ArrayList<PhysicalObject> needToUpdateObjects;
            public static long getDayTime() {
                return dayTime;
            }
            public static long getGlobalTime(){
                return globalTime;
            }
            public static TimeState getTimeState() {
                return timeState;
            }
            protected void updateThis(){}
            final protected void updateContent() {
                if (needToUpdateObjects != null) {
                    for (int i = 0; i < needToUpdateObjects.size(); i++) {
                        needToUpdateObjects.get(i).objectTimeController.updateThis();
                        needToUpdateObjects.get(i).objectTimeController.updateContent();
                    }
                }
            }
            public void addToListOfUpdatableObjects(PhysicalObject obj){
                needToUpdateObjects.add(obj);
            }
        }
        public static ObjectTimeController objectTimeController = new ObjectTimeController();
        private static long globalTime;
        private static long dayTime;
        private static TimeState timeState;


        final static long MIDNIGHT_VAL = 0;
        final static long NIGHT_VAL = 240;
        final static long MORNING_VAL = 480;
        final static long MIDDAY_VAL = 720;
        final static long DAY_VAL = 960;
        final static long EVENING_VAL = 1200;
        final static long FULL_DAY_LENGTH = 1440;



        public static void addToGlobalTime(long delta) throws Exceptions.TimeParadoxException{
            if(delta<0)
                throw new Exceptions.TimeParadoxException("Doctor Strange are using the time stone. Universal was broke");
            globalTime+=delta;
            dayTime=globalTime%FULL_DAY_LENGTH;

            if(dayTime>=MIDNIGHT_VAL   &&  dayTime<NIGHT_VAL)     timeState=TimeState.MIDNIGHT;
            if(dayTime>=NIGHT_VAL      &&  dayTime<MORNING_VAL)   timeState=TimeState.NIGHT;
            if(dayTime>=MORNING_VAL    &&  dayTime<MIDDAY_VAL)    timeState=TimeState.MORNING;
            if(dayTime>=MIDDAY_VAL     &&  dayTime<DAY_VAL)       timeState=TimeState.MIDDAY;
            if(dayTime>=DAY_VAL        &&  dayTime<EVENING_VAL)   timeState=TimeState.DAY;
            if(dayTime>=EVENING_VAL    ||  dayTime<MIDNIGHT_VAL)  timeState=TimeState.EVENING;
            System.out.println("Global time: " + globalTime);
            System.out.println("Day time: " + dayTime + " of " + FULL_DAY_LENGTH + ". Now is " +timeState.toString());
            //Weather.updateWeather();
            objectTimeController.updateContent();
        }

        public static void setDayTime(long t) throws Exceptions.TimeParadoxException{
            if(dayTime<t)
                addToGlobalTime(t-dayTime);
            else
                addToGlobalTime(t-dayTime+FULL_DAY_LENGTH);
        }

        public static void setTimeState(TimeState s)throws Exceptions.TimeParadoxException{
            long val=0;
            switch(s){
                case MIDNIGHT:
                    val=MIDNIGHT_VAL;
                    break;
                case NIGHT:
                    val=NIGHT_VAL;
                    break;
                case MORNING:
                    val=MORNING_VAL;
                    break;
                case MIDDAY:
                    val=MIDDAY_VAL;
                    break;
                case DAY:
                    val=DAY_VAL;
                    break;
                case EVENING:
                    val=EVENING_VAL;
                    break;
                default:
                    val=0;
            }
            setDayTime(val);
        }
    }
}
