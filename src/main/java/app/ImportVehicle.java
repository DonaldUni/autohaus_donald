package app;

import UI.View;
import model.Auto.Vehicle;
import services.VehicleService;

import java.io.File;
import java.util.ArrayList;

public class ImportVehicle {

    public static void main(String[] args) {

        VehicleService service = new VehicleService();

        File[] files = View.importDirectoryWithSwing();
        ArrayList<Vehicle> vehicles = service.extractData(files);
        service.storeDataInDB(vehicles);

        System.out.println("Die Fahrzeuge-Eingaben wurden erfolgreich in Datenbank gespeichert!");

        //service.deleteTableFromDB();
    }
}
