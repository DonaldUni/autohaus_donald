package Datenbank;

import model.Auto.Vehicle;
import model.Auto.VehicleType;
import model.Person.Client;

import java.util.ArrayList;

public interface StoreStrategie {

    public Boolean openConnection();

    public void closeConnection();

    public void writeClientList(ArrayList<Client> clients);

    public Boolean writeClient(Client client);

    public ArrayList<Client> readClientList();

    public Client readClient(int id, String firstName, String lastName, String address);

    public void writeVehicleList(ArrayList<Vehicle> vehicles);

    public Boolean writeVehicle(Vehicle vehicle);

    public ArrayList<Vehicle> readVehicleList();

    public Vehicle readVehicle(int id, VehicleType vehicleType, String vehicleDesignation, String manufacturer, String power, double salesPrices);

}
