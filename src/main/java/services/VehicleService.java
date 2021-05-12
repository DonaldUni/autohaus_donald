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

public class VehicleService implements ImportStrategie{

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

        for (Vehicle vehicle: vehicle_list) {
            System.out.println(vehicle.getId()+ " "+ vehicle.getVehicleType() +" "+ vehicle.getVehicleDesignation()
                    +" "+vehicle.getManufacturer()+ " "+ vehicle.getPower()+" "+vehicle.getSalesPrice());
        }

        return vehicle_list;
    }

    @Override
    public Boolean storeDataInDB(ArrayList vehicles) {

        DBH2Strategie storeInDB = new DBH2Strategie();

        storeInDB.writeVehicleList(vehicles);

        return true;
    }

    @Override
    public ArrayList<Vehicle> importDataFromDB() {

        DBH2Strategie readFromDB = new DBH2Strategie();

        ArrayList<Vehicle> vehicles1 =  readFromDB.readVehicleList();

        return vehicles1;
    }

    @Override
    public void deleteTableFromDB() {

        DBH2Strategie db = new DBH2Strategie();

        db.deleteVehicleList(db.getConnection());
    }
}
