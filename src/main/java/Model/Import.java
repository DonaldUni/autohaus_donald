package Model;

import Repository.ImportStrategie;
import App.FilterExtenxionFile;
import Model.Auto.Vehicle;
import Model.Person.Client;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Import implements ImportStrategie {

    private ArrayList<Vehicle> vehicle_list = new ArrayList<>();
    private ArrayList<Client> clients_list = new ArrayList<>();


    @Override
    public File[] importDirectoryWithSwing() {

        JFileChooser directory = new JFileChooser();
        directory.setMultiSelectionEnabled(true);     // erlaubt mehrfachige Auswahl
        directory.addChoosableFileFilter(new FilterExtenxionFile());  //erstellt Filter für Erweiterungen
        directory.showDialog(directory, "Select");

        // Datei holen
        File[] xml_files = directory.getSelectedFiles();

        return xml_files;
    }



    @Override
    public ArrayList<Client> extractClients(File[] files) {

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
            System.out.println(client.getFirstName()+" "+ client.getLastName()+" "+client.getAddress()+" ");
        }
        return clients_list;
    }

    @Override
    public ArrayList<Vehicle> extractVehicles(File[] files) {

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
            System.out.println(vehicle.getVehicleType()+" "+ vehicle.getVehicleDesignation()+" "+vehicle.getManufacturer()+" "+ vehicle.getPower()+" "+vehicle.getSalesPrice());
        }


        return vehicle_list;
    }

    @Override
    public Boolean storeVehiclesInDatenbank(ArrayList arrayList) {
        return null;
    }

    @Override
    public Boolean storeClientsInDatenbank(ArrayList arrayList) {
        return null;
    }

    public ArrayList<Vehicle> getVehicle_list() {
        return vehicle_list;
    }

    public void setVehicle_list(ArrayList<Vehicle> vehicle_list) {
        this.vehicle_list = vehicle_list;
    }

    public ArrayList<Client> getClients_list() {
        return clients_list;
    }

    public void setClients_list(ArrayList<Client> clients_list) {
        this.clients_list = clients_list;
    }
}
