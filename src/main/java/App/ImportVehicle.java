package App;

import model.Auto.Vehicle;
import Services.Import;
import java.io.File;
import java.util.ArrayList;

public class ImportVehicle {

    public static void main(String[] args) {

        Import anImport = new Import();

        File[] files = anImport.importDirectoryWithSwing();
        ArrayList<Vehicle> vehicles = anImport.extractVehicles(files);
        anImport.storeVehiclesInDatenbank(vehicles);

       // anImport.deleteVehicleListDB();
        System.out.println("Die Fahrzeuge-Eingaben wurden erfolgreich in Datenbank gespeichert!");
    }
}
