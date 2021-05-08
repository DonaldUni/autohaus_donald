package Repository;

import Model.Auto.Vehicle;
import Model.Person.Client;

import java.io.File;
import java.util.ArrayList;

public interface ImportStrategie {

    public File[] importDirectoryWithSwing();

    //public  File importDirectoryWith();

    public  ArrayList<Client> extractClients(File[] files);

    public  ArrayList<Vehicle> extractVehicles(File[] files);

    public  Boolean storeVehiclesInDatenbank(ArrayList<Vehicle> vehicles);

    public  Boolean storeClientsInDatenbank(ArrayList<Client> clients);

    public  ArrayList<Vehicle> importFromDBVehicleList();

    public  ArrayList<Client> importFromDBClientList();

}
