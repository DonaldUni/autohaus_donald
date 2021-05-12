package Services;

import Datenbank.DBH2Strategie;
import model.Auto.Vehicle;
import model.Person.Client;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Import implements ImportStrategie {

    @Override
    public ArrayList<Client> extractClients(File[] files) {

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
    public ArrayList<Vehicle> extractVehicles(File[] files) {

        ArrayList<Vehicle> vehicle_list = new ArrayList<>();
        ObjectMapper mapper = new XmlMapper();

        for (File file: files) {

            try {
                InputStream inputStream = new FileInputStream(file);
                TypeReference<List<Vehicle>> typeReference = new TypeReference<List<Vehicle>>() {};
                List<Vehicle> vehicles = mapper.readValue(inputStream, typeReference);

                vehicle_list.addAll(vehicles);

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        for (Vehicle vehicle: vehicle_list) {
            System.out.println(vehicle.getId()+ " "+ vehicle.getVehicleType() +" "+ vehicle.getVehicleDesignation()
                    +" "+vehicle.getManufacturer()+ " "+ vehicle.getPower()+" "+vehicle.getSalesPrice());
        }

        return vehicle_list;
    }

    @Override
    public Boolean storeVehiclesInDatenbank(ArrayList<Vehicle> vehicles) {

        DBH2Strategie storeInDB = new DBH2Strategie();

        storeInDB.writeVehicleList(vehicles);

        return true;
    }

    @Override
    public Boolean storeClientsInDatenbank(ArrayList<Client> clients) {

        DBH2Strategie storeInDB = new DBH2Strategie();

        storeInDB.writeClientList(clients);

        return true;
    }

    @Override
    public ArrayList<Vehicle> importFromDBVehicleList() {

        DBH2Strategie readFromDB = new DBH2Strategie();

        ArrayList<Vehicle> vehicles =  readFromDB.readVehicleList();

        return vehicles;
    }

    @Override
    public ArrayList<Client> importFromDBClientList() {

        DBH2Strategie readFromDB = new DBH2Strategie();

        ArrayList<Client> clients =  readFromDB.readClientList();

        return clients;
    }

    public void deleteClientListDB(){

        DBH2Strategie db = new DBH2Strategie();

        db.deleteClientList(db.getConnection());
    }

    public void deleteVehicleListDB(){

        DBH2Strategie db = new DBH2Strategie();

        db.deleteVehicleList(db.getConnection());
    }

}
