package app;

import model.Auto.Vehicle;
import services.ClientService;
import model.Person.Client;
import services.LetterService;
import services.VehicleService;
import java.util.ArrayList;

public class CreateLetter {

    //  import Kunden- und Fahrzeug-Daten aus der Datenbank und gengeriert durch die Methode createLetter die PDF-Dokumenten
    public static void main(String[] args) {

        ClientService clientService = new ClientService();

        VehicleService vehicleService = new VehicleService();

        ArrayList<Client> clients = clientService.importDataFromDB();

        ArrayList<Vehicle> vehicles  = vehicleService.importDataFromDB();

        LetterService.createLetter(clients, vehicles);

        System.out.println("Das ist die List von Kunden");
        for (Client client: clients) {
            System.out.println(client.getId()+" "+client.getFirstName()+" "+ client.getLastName()+" "+client.getAddress());
        }

        System.out.println("Das ist die List von Fahrzeuge");
        for (Vehicle vehicle: vehicles) {
            System.out.println(vehicle.getId() +" "+ vehicle.getVehicleType()+" "+ vehicle.getVehicleDesignation()+
                    " "+vehicle.getManufacturer()+" "+ vehicle.getPower()+" "+vehicle.getSalesPrice());
        }
    }

}
