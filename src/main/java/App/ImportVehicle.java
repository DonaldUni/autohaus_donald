package App;

import Model.Import;

public class ImportVehicle {

    public static void main(String[] args) {

        Import anImport = new Import();
        anImport.extractVehicles(anImport.importDirectoryWithSwing());
    }
}
