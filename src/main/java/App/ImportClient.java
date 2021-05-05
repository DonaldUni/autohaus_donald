package App;

import Model.Import;

public class ImportClient {



    public static void main(String[] args) {

        Import anImport = new Import();
        anImport.extractClients(anImport.importDirectoryWithSwing());

    }


}

