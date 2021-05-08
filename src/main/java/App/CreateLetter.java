package App;

import Model.Auto.Vehicle;
import Model.Import;
import Model.Person.Client;

import java.io.File;
import java.util.ArrayList;

public class CreateLetter {

    public static void main(String[] args) {

        Import anImport = new Import();

        ArrayList<Client> clients = anImport.importFromDBClientList();

        System.out.println("Das ist die List von Kunden");
        for (Client client: clients) {
            System.out.println(client.getId()+" "+client.getFirstName()+" "+ client.getLastName()+" "+client.getAddress());
        }

        /*
        ArrayList<Vehicle> vehicles  = anImport.importFromDBVehicleList();


        System.out.println("Das ist die List von Fahrzeuge");
        for (Vehicle vehicle: vehicles) {
            System.out.println(vehicle.getVehicleType()+" "+ vehicle.getVehicleDesignation()+
                    " "+vehicle.getManufacturer()+" "+ vehicle.getPower()+" "+vehicle.getSalesPrice());
        }*/
    }
}
