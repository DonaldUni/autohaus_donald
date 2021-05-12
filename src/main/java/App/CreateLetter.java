package App;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.Auto.Vehicle;
import Services.Import;
import model.Person.Client;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class CreateLetter {

    public static void main(String[] args) {

        Import anImport = new Import();

        ArrayList<Client> clients = anImport.importFromDBClientList();

        ArrayList<Vehicle> vehicles  = anImport.importFromDBVehicleList();

        createLetter(clients, vehicles);

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

    public static void createLetter(ArrayList<Client> clients, ArrayList<Vehicle> vehicles){

        for (Client client: clients) {
            try {
                Document document = new Document();
                // create and open the document, where the text will be wrote
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Anschreiben-"+ client.getFirstName()+"-"+ client.getLastName() +".pdf"));
                document.open();

                // write the text in the document
                document.add(new Paragraph( client.getAddress()));
                Paragraph salutation = new Paragraph("Sehr geehrte(r) Frau/Herr "+ client.getLastName() +",");
                salutation.setSpacingBefore(50f);

                Paragraph bodyText = new Paragraph("hiermit bekommen Sie die aktuelle Liste von  Fahrzeugen, die wir zur Verfügung stellen. Die sind :");

                // create and write the tabe with the list of Vehicle
                PdfPTable listOfVehicles = new PdfPTable(6);
                listOfVehicles.setWidthPercentage(100);
                listOfVehicles.setSpacingBefore(50f);
                listOfVehicles.setSpacingAfter(50f);

                PdfPCell cell1 = new PdfPCell(new Paragraph("ID"));
                PdfPCell cell2 = new PdfPCell(new Paragraph("Fahrzeugtyp"));
                PdfPCell cell3 = new PdfPCell(new Paragraph("Fahrzeugebezeichnung"));
                PdfPCell cell4 = new PdfPCell(new Paragraph("Hersteller"));
                PdfPCell cell5 = new PdfPCell(new Paragraph("Leistung"));
                PdfPCell cell6 = new PdfPCell(new Paragraph("Verkaufsprice"));

                listOfVehicles.addCell(cell1);
                listOfVehicles.addCell(cell2);
                listOfVehicles.addCell(cell3);
                listOfVehicles.addCell(cell4);
                listOfVehicles.addCell(cell5);
                listOfVehicles.addCell(cell6);

                for (Vehicle vehile: vehicles) {

                     cell1 = new PdfPCell(new Paragraph(vehile.getId()));
                     cell2 = new PdfPCell(new Paragraph(vehile.getVehicleType().name()));
                     cell3 = new PdfPCell(new Paragraph(vehile.getVehicleDesignation()));
                     cell4 = new PdfPCell(new Paragraph(vehile.getManufacturer()));
                     cell5 = new PdfPCell(new Paragraph(vehile.getPower()));
                     cell6 = new PdfPCell(new Paragraph(""+vehile.getSalesPrice()));

                    listOfVehicles.addCell(cell1);
                    listOfVehicles.addCell(cell2);
                    listOfVehicles.addCell(cell3);
                    listOfVehicles.addCell(cell4);
                    listOfVehicles.addCell(cell5);
                    listOfVehicles.addCell(cell6);
                }

                Paragraph endOfText = new Paragraph("Danke fürs Lesen und wir wünschen Ihnen Alles gute :) !!");
                endOfText.setSpacingAfter(50f);
                Paragraph signature = new Paragraph("Autohaus");


                // add all Element to the PDF document
                document.add(salutation);
                document.add(bodyText);
                document.add(listOfVehicles);
                document.add(endOfText);
                document.add(signature);

                document.close();
                writer.close();
            } catch (DocumentException | FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
