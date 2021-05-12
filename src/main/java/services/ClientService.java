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

public class ClientService implements ImportStrategie{

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

        for (Client client: clients_list) {
            System.out.println(client.getId()+" "+client.getFirstName()+" "+ client.getLastName()+" "+client.getAddress());
        }
        return clients_list;
    }

    @Override
    public Boolean storeDataInDB(ArrayList clients) {

        DBH2Strategie storeInDB = new DBH2Strategie();

        storeInDB.writeClientList(clients);

        return true;
    }

    @Override
    public ArrayList<Client> importDataFromDB() {

        DBH2Strategie readFromDB = new DBH2Strategie();

        ArrayList<Client> clients =  readFromDB.readClientList();

        return clients;
    }

    @Override
    public void deleteTableFromDB() {

        DBH2Strategie db = new DBH2Strategie();

        db.deleteClientList(db.getConnection());

    }
}
