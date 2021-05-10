package App;

import Services.Import;
import model.Person.Client;

import java.io.File;
import java.util.ArrayList;

public class ImportClient {

    public static void main(String[] args) {

        Import anImport = new Import();

        File[] files = anImport.importDirectoryWithSwing();
        ArrayList<Client> clients = anImport.extractClients(files);
        anImport.storeClientsInDatenbank(clients);

        System.out.println("Die Kunde-Eingaben wurden erfolgreich in Datenbank gespeichert!");

        //anImport.deleteClientListDB();
    }


}

