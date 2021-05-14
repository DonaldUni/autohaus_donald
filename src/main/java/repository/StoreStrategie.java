package repository;

import model.Auto.Vehicle;
import model.Auto.VehicleType;
import model.Person.Client;

import java.util.ArrayList;

public interface StoreStrategie {

     void openConnection();

     void closeConnection();

     void writeClientList(ArrayList<Client> clients);

     void writeClient(Client client);

     ArrayList<Client> readClientList();

     Client readClient(int id, String firstName, String lastName, String address);

     void writeVehicleList(ArrayList<Vehicle> vehicles);

     void writeVehicle(Vehicle vehicle);

     ArrayList<Vehicle> readVehicleList();

     Vehicle readVehicle(int id, VehicleType vehicleType, String vehicleDesignation, String manufacturer, String power, double salesPrices);

}
