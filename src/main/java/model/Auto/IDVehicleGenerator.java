package model.Auto;

public class IDVehicleGenerator {

    private static long id = -1;

    public static long getNextID(){

        id = ++id;
        return id;
    }
}
