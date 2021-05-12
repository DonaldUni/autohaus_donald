package Services;

import model.Auto.Vehicle;
import model.Person.Client;

import java.io.File;
import java.util.ArrayList;

public interface ImportStrategie {

    public  ArrayList<Client> extractClients(File[] files);

    public  ArrayList<Vehicle> extractVehicles(File[] files);

    public  Boolean storeVehiclesInDatenbank(ArrayList<Vehicle> vehicles);

    public  Boolean storeClientsInDatenbank(ArrayList<Client> clients);

    public  ArrayList<Vehicle> importFromDBVehicleList();

    public  ArrayList<Client> importFromDBClientList();

}
