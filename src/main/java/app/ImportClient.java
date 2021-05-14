package app;

import services.ClientService;
import UI.View;
import model.Person.Client;

import java.io.File;
import java.util.ArrayList;

public class ImportClient {

    // Wählt sie XML-Datei aus, extrahieren die Kunden-Daten und speichert es persistiert in der Datenbank
    public static void main(String[] args) {

        ClientService clientService = new ClientService();

        File[] files = View.importDirectoryWithSwing();
        ArrayList<Client> clients = clientService.extractData(files);
        clientService.storeDataInDB(clients);

        System.out.println("Die Kunde-Eingaben wurden erfolgreich in Datenbank gespeichert!");

        //clientService.deleteTableFromDB();
    }


}

