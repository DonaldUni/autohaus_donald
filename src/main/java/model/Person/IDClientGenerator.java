package model.Person;

public class IDClientGenerator {

    private static long nextID = -1;

    public static long getNextID(){

        nextID = ++nextID;
        return nextID;
    }
}
