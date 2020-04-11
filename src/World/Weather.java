package World;

public class Weather {
    private static Weather ourInstance = new Weather();

    public static Weather getInstance() {
        return ourInstance;
    }

    private Weather() {
    }
}
