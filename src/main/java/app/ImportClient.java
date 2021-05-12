package app;

import services.ClientService;
import UI.View;
import model.Person.Client;

import java.io.File;
import java.util.ArrayList;

public class ImportClient {

    public static void main(String[] args) {

        ClientService service = new ClientService();

        File[] files = View.importDirectoryWithSwing();
        ArrayList<Client> clients = service.extractData(files);
        service.storeDataInDB(clients);

        System.out.println("Die Kunde-Eingaben wurden erfolgreich in Datenbank gespeichert!");

        //service.deleteTableFromDB();
    }


}

