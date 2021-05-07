package Model;

public class IDGenerator {

    private static long nextID = 0;

    public static long getNextID(){

        nextID++;
        return nextID;
    }
}
