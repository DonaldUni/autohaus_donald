package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import repository.DBH2Strategie;
import model.Person.Client;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ClientService implements ImportService {

    private final DBH2Strategie datenbank = new DBH2Strategie();

    // extrahiere die Kunden-Daten aus der eingegebenen XML-Datei und liefert dementsprechend eine List von erstellten Kunde
    @Override
    public ArrayList<Client> extractData(File[] files) {

        ArrayList<Client> clients_list = new ArrayList<>();
        ObjectMapper mapper = new XmlMapper();

        for (File file: files) {
            try {

                InputStream inputStream = new FileInputStream(file);
                TypeReference<List<Client>> typeReference = new TypeReference<List<Client>>() {};
                List<Client> clients = mapper.readValue(inputStream, typeReference);

                clients_list.addAll(clients);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /*for (Client client: clients_list) {
            System.out.println(client.getId()+" "+client.getFirstName()+" "+ client.getLastName()+" "+client.getAddress());
        }*/
        return clients_list;
    }

    // Speichert die gegebene List von Kunden in der Datenbank
    @Override
    public void storeDataInDB(ArrayList clients) {

        datenbank.writeClientList(clients);
    }

    // holt die List von Kunden aus der Datenbank
    @Override
    public ArrayList<Client> importDataFromDB() {

        return datenbank.readClientList();
    }

    //entfernt die Kunden-Tabelle der Datenbank
    @Override
    public void deleteTableFromDB() {

        datenbank.deleteClientList(datenbank.getConnection());
    }
}
