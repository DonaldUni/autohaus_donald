package App;

import Model.Auto.Vehicle;
import Model.Import;
import Model.Person.Client;

import java.io.File;
import java.util.ArrayList;

public class ImportVehicle {

    public static void main(String[] args) {

        Import anImport = new Import();

        File[] files = anImport.importDirectoryWithSwing();
        ArrayList<Vehicle> vehicles = anImport.extractVehicles(files);
        anImport.storeVehiclesInDatenbank(vehicles);
    }
}
