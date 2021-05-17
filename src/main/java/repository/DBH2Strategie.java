package repository;

import model.Auto.Vehicle;
import model.Auto.VehicleType;
import model.Person.Client;

import java.sql.*;
import java.util.ArrayList;

public class DBH2Strategie implements StoreStrategie {

    // Hier sind Konstanten die später in Methode verwendet werden
    private final String DATABASEPATH = "jdbc:h2:file:C:\\Users\\Donald Samo\\Documents\\Git Projekte\\Autohaus_donald\\Datenbank\\Datenbank-JDBC";

    private final String CLIENTLIST = "ClientList";
    private final String IDCLIENT = "id";
    private final String FIRSTNAME = "firstName";
    private final String LASTNAME = "lastName";
    private final String ADDRESS = "address";

    private final String VEHICLELIST = "VehicleList";
    private final String IDVEHICLE = "id";
    private final String VEHICLETYPE = "vehicleType";
    private final String VEHICLEDESIGNATION = "vehicleDesignation";
    private final String MANUFACTURER = "manufacturer";
    private final String POWER = "power";
    private final String SALESPRICE = "salesPrice";



    private Connection connection;

    public DBH2Strategie() {
        // öffne die Verbindung mit der Datenbank und generiert die Kunden- nd Fahrzeug-Tabelle
        try {
            Class.forName("org.h2.Driver");

            connection = DriverManager.getConnection(DATABASEPATH);   //jdbc:h2:file:D:/Datenbank

            createClientList(connection);
            createVehicleList(connection);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // erstellt die Kunden-Tabelle in der Datenbank
    public void createClientList(Connection con){
        try (PreparedStatement pstmt = con.prepareStatement("CREATE TABLE IF NOT EXISTS "+ CLIENTLIST +
                " ("+IDCLIENT+" INTEGER AUTO_INCREMENT PRIMARY KEY, "+ FIRSTNAME +" TEXT, "+ LASTNAME +" TEXT, "+ ADDRESS +" TEXT);")){

            pstmt.executeUpdate();
            System.out.println("ClientList Table successfull created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // erstellt die Fahrzeug-Tabelle in der Datenbank
    public void createVehicleList(Connection con){
        try (PreparedStatement pstmt = con.prepareStatement
                ("CREATE TABLE IF NOT EXISTS "+ VEHICLELIST +
                    " ("+ IDVEHICLE +" INTEGER AUTO_INCREMENT PRIMARY KEY, "+ VEHICLETYPE +" TEXT, " + VEHICLEDESIGNATION +" TEXT, "+
                        MANUFACTURER +" TEXT, "+ POWER +" TEXT, "+ SALESPRICE +" DOUBLE);")){

            pstmt.executeUpdate();
            System.out.println("VehicleList Table successfull created");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // prüft ob der zu schreibende Kunde schon in der Datenbank gespeichert ist und liefert entsprechend ein boolean
    public boolean existClient(Client client){
        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM  "+ CLIENTLIST +" ;")) {

            ResultSet set = pstmt.executeQuery();
            while (set.next()) {
                if (set.getString(FIRSTNAME).equals(client.getFirstName())
                        &&  set.getString(LASTNAME).equals(client.getLastName())
                        &&  set.getString(ADDRESS).equals(client.getAddress()))
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // prüft ob das zu schreibende Fahrzeug schon in der Datenbank gespeichert ist und liefert entsprechend ein boolean
    public boolean existVehicle(Vehicle vehicle){

        try (PreparedStatement pstmt = connection.prepareStatement(
                "SELECT * FROM  "+ VEHICLELIST +" ;")) {

            ResultSet set = pstmt.executeQuery();
            while (set.next()) {
                if (set.getString(VEHICLETYPE).equals(vehicle.getVehicleType().name())
                        &&  set.getString(VEHICLEDESIGNATION).equals(vehicle.getVehicleDesignation())
                        &&  set.getString(MANUFACTURER).equals(vehicle.getManufacturer())
                        &&  set.getDouble(SALESPRICE) == vehicle.getSalesPrice()){
                    System.out.println("Das Element "+ set.getString(VEHICLEDESIGNATION) +"existiert schon!");
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // öffne die Verbindung mit der Datenbank falls sie geschlossen ist
    @Override
    public void openConnection() {
        try {
            if (connection.isClosed()){
                connection = DriverManager.getConnection(DATABASEPATH);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // schliesst die Verbindung mit der Datenbank
    @Override
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // schreibt eine List von Kunden in der Datenbank
    @Override
    public void writeClientList(ArrayList<Client> clients) {

        for (Client client: clients) {
            writeClient(client);
        }

        closeConnection();

        System.out.println("Data sind successfull saved in Database ");
    }

    // schreibt nur einen Kunde in der Datenbank
    @Override
    public void writeClient(Client client) {

        if (!existClient(client)){
            try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO "+ CLIENTLIST +
                    " ("+ FIRSTNAME +", "+ LASTNAME +", "+ ADDRESS +") VALUES(?,?,?) ;")){
                //pstmt.setLong(1, client.getId());
                pstmt.setString(1, client.getFirstName());
                pstmt.setString(2, client.getLastName());
                pstmt.setString(3, client.getAddress());
                pstmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Liest die List von Kunden, die in der Datenbank ist.
    @Override
    public ArrayList<Client> readClientList() {

        ArrayList<Client> clients = new ArrayList<>();
        openConnection();

        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM "+ CLIENTLIST +" ;")){
            ResultSet set = pstmt.executeQuery();

            while (set.next()){

                clients.add(new Client(set.getInt(IDCLIENT),set.getString(FIRSTNAME),
                        set.getString(LASTNAME), set.getString(ADDRESS)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
        return clients;
    }

    // schreibt eine List von Fahrzeug in der Datenbank
    @Override
    public void writeVehicleList(ArrayList<Vehicle> vehicles) {

        openConnection();

        for (Vehicle vehicle: vehicles) {
            writeVehicle(vehicle);
        }

        closeConnection();

        System.out.println("Data sind successfull saved in Database. ");
    }

    // schreibt nur ein Fahrzeug in der Datenbank
    @Override
    public void writeVehicle(Vehicle vehicle) {

        if (!existVehicle(vehicle)){
            try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO "+ VEHICLELIST +
                    " (" + VEHICLETYPE +", "+ VEHICLEDESIGNATION +", "+ MANUFACTURER +", "+ POWER +
                    ", "+ SALESPRICE +") VALUES(?,?,?,?,?);")){

                //pstmt.setLong(1, vehicle.getId());
                pstmt.setString(1, vehicle.getVehicleType().name());
                pstmt.setString(2, vehicle.getVehicleDesignation());
                pstmt.setString(3, vehicle.getManufacturer());
                pstmt.setString(4, vehicle.getPower());
                pstmt.setDouble(5, vehicle.getSalesPrice());
                pstmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Liest die List von Fahrzeug, die in der Datenbank ist.
    @Override
    public ArrayList<Vehicle> readVehicleList() {

        ArrayList<Vehicle> vehicles = new ArrayList<>();
        openConnection();

        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM "+ VEHICLELIST +" ;")){

            ResultSet set = pstmt.executeQuery();
            while (set.next()){

                vehicles.add(new Vehicle(set.getInt(IDVEHICLE), convertInVehicletype(set.getString(VEHICLETYPE)),
                       set.getString(VEHICLEDESIGNATION), set.getString(MANUFACTURER), set.getString(POWER),
                        set.getDouble(SALESPRICE)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
        return vehicles;
    }

    // entfernt die Kunden-Tabelle aus der Datenbank
    public void deleteClientList(Connection connection) {

        openConnection();

        try (PreparedStatement pstmt = connection.prepareStatement(
                "DROP TABLE "+ CLIENTLIST +" ;")) {
            pstmt.executeUpdate();
            connection.close();

            System.out.println("Database ClientList deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
    }

    // entfernt die Fahrzeug-Tabelle aus der Datenbank
    public void deleteVehicleList(Connection connection) {
        openConnection();

        try (PreparedStatement pstmt = connection.prepareStatement(
                "DROP TABLE "+ VEHICLELIST +" ;")) {
            pstmt.executeUpdate();
            connection.close();

            System.out.println("Database VehicleList deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
    }

    //konvertiert text in Fahrzeugtyp
    private VehicleType convertInVehicletype(String name){
        VehicleType vehicleType = VehicleType.LKW;

        if (name.equals("PKW")){
            vehicleType = VehicleType.PKW;
        }
        if (name.equals("Motorrad")){
            vehicleType = VehicleType.Motorrad;
        }
        return vehicleType;
    }

    //getter
    public Connection getConnection() {
        return connection;
    }
}
