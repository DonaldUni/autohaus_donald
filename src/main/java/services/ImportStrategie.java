package services;

import model.Auto.Vehicle;
import model.Person.Client;

import java.io.File;
import java.util.ArrayList;

public interface ImportStrategie {

    public  ArrayList extractData(File[] files);

    public  Boolean storeDataInDB(ArrayList list);

    public  ArrayList importDataFromDB();

    public void deleteTableFromDB();

}
