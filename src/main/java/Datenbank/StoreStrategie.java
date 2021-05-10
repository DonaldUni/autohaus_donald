package Datenbank;

import model.Auto.Vehicle;
import model.Auto.VehicleType;
import model.Person.Client;

import java.util.ArrayList;

public interface StoreStrategie {

    public Boolean openWritableClientList();

    public Boolean writeClient(Client client);

    public void writeClientList(ArrayList<Client> arrayList);

    public void closeWritableClientList();

    public Boolean openWritableVehicleList();

    public Boolean writeVehicle(Vehicle vehicle);

    public void writeVehicleList(ArrayList<Vehicle> vehicles);

    public void closeWritableVehicleList();

    public Boolean openReadableClientList();

    public Client readClient(int id, String firstName, String lastName, String address);

    public ArrayList<Client> readClientList();

    public Boolean closeReadableClientList();

    public Boolean openReadableVehicleList();

    public Vehicle readVehicle(int id, VehicleType vehicleType, String vehicleDesignation, String manufacturer, String power, double salesPrices);

    public ArrayList<Vehicle> readVehicleList();

    public Boolean closeReadableVehicleList();


}
