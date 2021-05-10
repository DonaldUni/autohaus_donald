package App;

import model.Auto.Vehicle;
import Services.Import;
import model.Person.Client;

import java.util.ArrayList;

public class CreateLetter {

    public static void main(String[] args) {

        Import anImport = new Import();

        ArrayList<Client> clients = anImport.importFromDBClientList();

        System.out.println("Das ist die List von Kunden");
        for (Client client: clients) {
            System.out.println(client.getId()+" "+client.getFirstName()+" "+ client.getLastName()+" "+client.getAddress());
        }


        ArrayList<Vehicle> vehicles  = anImport.importFromDBVehicleList();

        System.out.println("Das ist die List von Fahrzeuge");
        for (Vehicle vehicle: vehicles) {
            System.out.println(vehicle.getId() +" "+ vehicle.getVehicleType()+" "+ vehicle.getVehicleDesignation()+
                    " "+vehicle.getManufacturer()+" "+ vehicle.getPower()+" "+vehicle.getSalesPrice());
        }
    }
}
