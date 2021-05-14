package services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.Auto.Vehicle;
import model.Person.Client;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class LetterService {

    // erstellt Anschreiben in Format PDF mit den Daten von Kunden und von Fahrzeuge
    public static void createLetter(ArrayList<Client> clients, ArrayList<Vehicle> vehicles){

        for (Client client: clients) {
            try {
                Document document = new Document();
                // erstellt und öffnet das Dokument, wo den text geschrieben wird
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Anschreiben-"+ client.getFirstName()+"-"+ client.getLastName() +".pdf"));
                document.open();

                // schreibt den Text in den Dokument
                document.add(new Paragraph( client.getAddress()));
                Paragraph salutation = new Paragraph("Sehr geehrte(r) Frau/Herr "+ client.getLastName() +",");
                salutation.setSpacingBefore(50f);

                Paragraph bodyText = new Paragraph("hiermit bekommen Sie die aktuelle Liste von  Fahrzeugen, die wir zur Verfügung stellen. Die sind :");

                // erstellt ein Tabelle mit der List von Fahrzeuge
                PdfPTable listOfVehicles = new PdfPTable(5);
                listOfVehicles.setWidthPercentage(100);
                listOfVehicles.setSpacingBefore(50f);
                listOfVehicles.setSpacingAfter(50f);

                PdfPCell cell1 = new PdfPCell(new Paragraph("Fahrzeugtyp"));
                PdfPCell cell2 = new PdfPCell(new Paragraph("Fahrzeugebezeichnung"));
                PdfPCell cell3 = new PdfPCell(new Paragraph("Hersteller"));
                PdfPCell cell4 = new PdfPCell(new Paragraph("Leistung"));
                PdfPCell cell5 = new PdfPCell(new Paragraph("Verkaufsprice"));

                listOfVehicles.addCell(cell1);
                listOfVehicles.addCell(cell2);
                listOfVehicles.addCell(cell3);
                listOfVehicles.addCell(cell4);
                listOfVehicles.addCell(cell5);

                for (Vehicle vehile: vehicles) {

                    cell1 = new PdfPCell(new Paragraph(vehile.getVehicleType().name()));
                    cell2 = new PdfPCell(new Paragraph(vehile.getVehicleDesignation()));
                    cell3 = new PdfPCell(new Paragraph(vehile.getManufacturer()));
                    cell4 = new PdfPCell(new Paragraph(vehile.getPower()));
                    cell5 = new PdfPCell(new Paragraph(""+vehile.getSalesPrice()+ " EURO"));

                    listOfVehicles.addCell(cell1);
                    listOfVehicles.addCell(cell2);
                    listOfVehicles.addCell(cell3);
                    listOfVehicles.addCell(cell4);
                    listOfVehicles.addCell(cell5);
                }

                Paragraph endOfText = new Paragraph("Danke fürs Lesen und wir wünschen Ihnen Alles gute :) !!");
                endOfText.setSpacingAfter(50f);
                Paragraph signature = new Paragraph("Autohaus");


                // addiert alle Elemente zu den PDF-Dokument
                document.add(salutation);
                document.add(bodyText);
                document.add(listOfVehicles);
                document.add(endOfText);
                document.add(signature);

                document.close();
                writer.close();
            } catch (DocumentException | FileNotFoundException  e) {
                e.printStackTrace();
            }
        }
    }

}
