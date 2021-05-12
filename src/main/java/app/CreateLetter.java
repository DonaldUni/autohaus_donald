package app;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.Auto.Vehicle;
import services.ClientService;
import model.Person.Client;
import services.LetterService;
import services.VehicleService;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class CreateLetter {

    public static void main(String[] args) {

        ClientService clientService = new ClientService();
        VehicleService vehicleService = new VehicleService();

        ArrayList<Client> clients = clientService.importDataFromDB();

        ArrayList<Vehicle> vehicles  = vehicleService.importDataFromDB();

        LetterService.createLetter(clients, vehicles);

        /*System.out.println("Das ist die List von Kunden");
        for (Client client: clients) {
            System.out.println(client.getId()+" "+client.getFirstName()+" "+ client.getLastName()+" "+client.getAddress());
        }

        System.out.println("Das ist die List von Fahrzeuge");
        for (Vehicle vehicle: vehicles) {
            System.out.println(vehicle.getId() +" "+ vehicle.getVehicleType()+" "+ vehicle.getVehicleDesignation()+
                    " "+vehicle.getManufacturer()+" "+ vehicle.getPower()+" "+vehicle.getSalesPrice());
        }*/
    }


}
