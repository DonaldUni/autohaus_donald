package Datenbank;

import model.Auto.Vehicle;
import model.Auto.VehicleType;
import model.Person.Client;

import java.sql.*;
import java.util.ArrayList;

public class DBH2Strategie implements StoreStrategie {

    private final String DATABASEPATH = "jdbc:h2:file:D:/Datenbank";


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

        try {
            Class.forName("org.h2.Driver");

            connection = DriverManager.getConnection(DATABASEPATH);   //jdbc:h2:file:D:/Datenbank
            //jdbc:h2:Datenbank.db
            createClientList(connection);
            createVehicleList(connection);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void createClientList(Connection con){
        try (PreparedStatement pstmt = con.prepareStatement("CREATE TABLE IF NOT EXISTS "+ CLIENTLIST +
                " ("+IDCLIENT+" INTEGER AUTO_INCREMENT PRIMARY KEY, "+ FIRSTNAME +" TEXT, "+ LASTNAME +" TEXT, "+ ADDRESS +" TEXT);")){

            pstmt.executeUpdate();
            System.out.println("ClientList Table successfull created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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


    @Override
    public Boolean openConnection() {
        try {
            if (connection.isClosed()){
                connection = DriverManager.getConnection(DATABASEPATH);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void writeClientList(ArrayList<Client> clients) {

        for (Client client: clients) {
            writeClient(client);
        }

        closeConnection();

        System.out.println("Data sind successfull saved in Database ");
    }

    @Override
    public Boolean writeClient(Client client) {

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
            return true;                // client is successfull inserted
        }

        return false;                   //client was already in the table
    }

    @Override
    public ArrayList<Client> readClientList() {

        ArrayList<Client> clients = new ArrayList<>();
        openConnection();

        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM "+ CLIENTLIST +" ;")){
            ResultSet set = pstmt.executeQuery();

            while (set.next()){

                clients.add(readClient(set.getInt(IDCLIENT),set.getString(FIRSTNAME),
                        set.getString(LASTNAME), set.getString(ADDRESS)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
        return clients;
    }

    @Override
    public Client readClient(int id, String firstName, String lastName, String address) {

        Client client = new Client();
        client.setId(id);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setAddress(address);

        return client;
    }

    @Override
    public void writeVehicleList(ArrayList<Vehicle> vehicles) {

        openConnection();

        for (Vehicle vehicle: vehicles) {
            writeVehicle(vehicle);
        }

        closeConnection();

        System.out.println("Data sind successfull saved in Database. ");
    }

    @Override
    public Boolean writeVehicle(Vehicle vehicle) {

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
            return true;                // vehicle is successfull inserted
        }

        return false;                   //vehicle was already in the table
    }

    @Override
    public ArrayList<Vehicle> readVehicleList() {

        ArrayList<Vehicle> vehicles = new ArrayList<>();
        openConnection();

        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM "+ VEHICLELIST +" ;")){

            ResultSet set = pstmt.executeQuery();
            while (set.next()){

                Vehicle vehicle = readVehicle(set.getInt(IDVEHICLE), convertInVehicletype(set.getString(VEHICLETYPE)),
                       set.getString(VEHICLEDESIGNATION), set.getString(MANUFACTURER), set.getString(POWER),
                        set.getDouble(SALESPRICE));

                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
        return vehicles;
    }

    @Override
    public Vehicle readVehicle(int id, VehicleType vehicleType, String vehicleDesignation, String manufacturer, String power, double salesPrices) {

        Vehicle vehicle = new Vehicle();

        vehicle.setId(id);
        vehicle.setVehicleType(vehicleType);
        vehicle.setVehicleDesignation(vehicleDesignation);
        vehicle.setManufacturer(manufacturer);
        vehicle.setPower(power);
        vehicle.setSalesPrice(salesPrices);
        return vehicle;
    }

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

    public Connection getConnection() {
        return connection;
    }
}
