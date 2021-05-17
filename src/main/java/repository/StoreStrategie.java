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

     void writeVehicleList(ArrayList<Vehicle> vehicles);

     void writeVehicle(Vehicle vehicle);

     ArrayList<Vehicle> readVehicleList();

}
