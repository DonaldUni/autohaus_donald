package services;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.Document;
import model.Auto.Vehicle;
import model.Person.Client;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class LetterService {

    // erstellt Anschreiben in Format PDF mit den Daten von Kunden und von Fahrzeuge
    public static void createLetter(ArrayList<Client> clients, ArrayList<Vehicle> vehicles){

        for (Client client: clients) {
            try {

                // erstellt und öffnet das Dokument, wo den text geschrieben wird
                String output_url = "C:\\Users\\Donald Samo\\Documents\\Git Projekte\\Autohaus_donald\\PDF-Ausgabe-dateien\\"+ client.getLastName()+ ".pdf";
                com.itextpdf.kernel.pdf.PdfWriter writer = new PdfWriter(output_url);
                //PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Anschreiben-"+ client.getFirstName()+"-"+ client.getLastName() +".pdf"));
                PdfDocument pdfDocument = new PdfDocument(writer);
                pdfDocument.addNewPage();
                Document document = new Document(pdfDocument);

                // add rectangle on top of the document
                Table rectangle = createRectangle();

                // create Text for pdf
                PdfFont boldFont = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
                PdfFont timesFont = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);

                com.itextpdf.layout.element.Paragraph adresse = new com.itextpdf.layout.element.Paragraph(new Text(client.getAddress()).setFont(timesFont));
                adresse.setMarginBottom(50f);



                Text nachnameClient = new Text(client.getLastName()).setFont(boldFont);
                Text anrede = new Text("Sehr geehrte(r) Frau/Herr ").setFont(timesFont);


                com.itextpdf.layout.element.Paragraph salutation = new com.itextpdf.layout.element.Paragraph()
                        .add(anrede)
                        .add(nachnameClient)
                        .add(",");

                com.itextpdf.layout.element.Paragraph bodyText = new com.itextpdf.layout.element.Paragraph(new Text("hiermit bekommen Sie die aktuelle Liste von  Fahrzeugen, die wir zur Verfügung stellen. Die sind :").setFont(timesFont));

                // erstellt ein Tabelle mit der List von Fahrzeuge
                Table listOfVehicles = createTableOfVihecles(vehicles);

                com.itextpdf.layout.element.Paragraph endOfText = new com.itextpdf.layout.element.Paragraph(new Text("Danke fürs Lesen und wir wünschen Ihnen Alles gute :) !!").setFont(timesFont));
                endOfText.setMarginBottom(50f);
                com.itextpdf.layout.element.Paragraph signature = new com.itextpdf.layout.element.Paragraph(new Text("Autohaus").setFont(timesFont));

                // addiert alle Elemente zu den PDF-Dokument
                document.add(rectangle);
                document.add(adresse);
                document.add(salutation);
                document.add(bodyText);
                document.add(listOfVehicles);
                document.add(endOfText);
                document.add(signature);
                document.add(createLogo());

                document.close();
            } catch (ClassCastException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Table createRectangle(){

        float[] numberOfColon = {500, 500};
        Table rectangle = new Table(numberOfColon);
        rectangle.setBackgroundColor(new DeviceRgb(121, 204, 240));
        rectangle.setHeight(50f);
        rectangle.setFixedPosition(0,800,600);
        rectangle.setMarginBottom(50f);

        return rectangle;
    }

    private static Table createTableOfVihecles(ArrayList<Vehicle> vehicles){

        float[] numberOfcolon = {100,150,100,100,100};
        Table listOfVehicles = new Table(numberOfcolon);
        listOfVehicles.setMarginTop(50f);
        listOfVehicles.setMarginBottom(50f);

        Cell cell1 = new Cell().add(new Paragraph("Fahrzeugtyp").setBold());
        Cell cell2 = new Cell().add(new Paragraph("Fahrzeugebezeichnung")).setBold();
        Cell cell3 = new Cell().add(new Paragraph("Hersteller")).setBold();
        Cell cell4 = new Cell().add(new Paragraph("Leistung")).setBold();
        Cell cell5 = new Cell().add(new Paragraph("Verkaufsprice")).setBold();

        listOfVehicles.addCell(cell1).setBackgroundColor(new DeviceRgb(121, 204, 240));
        listOfVehicles.addCell(cell2).setBackgroundColor(new DeviceRgb(121, 204, 240));
        listOfVehicles.addCell(cell3).setBackgroundColor(new DeviceRgb(121, 204, 240));
        listOfVehicles.addCell(cell4).setBackgroundColor(new DeviceRgb(121, 204, 240));
        listOfVehicles.addCell(cell5).setBackgroundColor(new DeviceRgb(121, 204, 240));

        for (Vehicle vehile: vehicles) {

            cell1 = new Cell().add(vehile.getVehicleType().name());
            cell2 = new Cell().add(vehile.getVehicleDesignation());
            cell3 = new Cell().add(vehile.getManufacturer());
            cell4 = new Cell().add(vehile.getPower());
            cell5 = new Cell().add(""+vehile.getSalesPrice()+ " EURO");

            listOfVehicles.addCell(cell1);
            listOfVehicles.addCell(cell2);
            listOfVehicles.addCell(cell3);
            listOfVehicles.addCell(cell4);
            listOfVehicles.addCell(cell5);
        }
        return listOfVehicles;
    }

    private static com.itextpdf.layout.element.Image createLogo(){

        String scr = "Image/Haeger_image.jpg";
        ImageData data = null;
        try {
             data = ImageDataFactory.create(scr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        com.itextpdf.layout.element.Image haeger_logo = new com.itextpdf.layout.element.Image(data);
        haeger_logo.setFixedPosition(450, 0);
        haeger_logo.setWidth(100f);
        haeger_logo.setHeight(100f);

        return haeger_logo;
    }

}
