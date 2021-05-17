package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.Auto.Vehicle;
import repository.DBH2Strategie;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class VehicleService implements ImportService {

    private final DBH2Strategie datenbank = new DBH2Strategie();

    // extrahiere die Fahrzeug-Daten aus der eingegebenen XML-Datei und liefert dementsprechend eine List von erstellten Fahrzeuge
    @Override
    public ArrayList<Vehicle> extractData(File[] files) {
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

        /*for (Vehicle vehicle: vehicle_list) {
            System.out.println(vehicle.getId()+ " "+ vehicle.getVehicleType() +" "+ vehicle.getVehicleDesignation()
                    +" "+vehicle.getManufacturer()+ " "+ vehicle.getPower()+" "+vehicle.getSalesPrice());
        }*/

        return vehicle_list;
    }

    // Speichert die gegebene List von Fahrzeug in der Datenbank
    @Override
    public void storeDataInDB(ArrayList vehicles) {

        datenbank.writeVehicleList(vehicles);
    }

    // holt die List von Fahrzeug aus der Datenbank
    @Override
    public ArrayList<Vehicle> importDataFromDB() {

        return datenbank.readVehicleList();
    }

    //entfernt die Fahrzeug-Tabelle der Datenbank
    @Override
    public void deleteTableFromDB() {

        datenbank.deleteVehicleList(datenbank.getConnection());
    }
}
