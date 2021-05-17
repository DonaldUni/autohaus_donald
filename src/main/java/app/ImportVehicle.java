package app;

import UI.View;
import model.Auto.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.VehicleService;

import java.io.File;
import java.util.ArrayList;

public class ImportVehicle {

    // Wählt sie XML-Datei aus, extrahieren die Fahrzeuge-Daten und speichert es persistiert in der Datenbank
    public static void main(String[] args) {

        VehicleService vehicleService = new VehicleService();

        File[] files = View.importDirectoryWithSwing();
        ArrayList<Vehicle> vehicles = vehicleService.extractData(files);
        vehicleService.storeDataInDB(vehicles);

        //System.out.println("Die Fahrzeuge-Eingaben wurden erfolgreich in Datenbank gespeichert!");
        Logger logger = LoggerFactory.getLogger(ImportVehicle.class);
        logger.debug("Die Fahrzeuge-Eingaben wurden erfolgreich in Datenbank gespeichert!");
        //vehicleService.deleteTableFromDB();
    }
}
