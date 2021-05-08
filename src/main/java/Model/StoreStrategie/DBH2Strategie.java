package Model.StoreStrategie;

import Model.Auto.Vehicle;
import Model.Person.Client;
import Repository.StoreStrategie;

import java.sql.*;
import java.util.ArrayList;

public class DBH2Strategie implements StoreStrategie {

    private Connection connection;

    public DBH2Strategie() {

        try {
            Class.forName("org.h2.Driver");

            connection = DriverManager.getConnection("jdbc:h2:file:D:/Datenbank");
            //jdbc:h2:Datenbank.db
            createClientList(connection);
            createVehicleList(connection);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void createClientList(Connection con){
        try (PreparedStatement pstmt = con.prepareStatement("CREATE TABLE IF NOT EXISTS ClientList "
                +"(id INTEGER, firstName TEXT, lastName TEXT, address TEXT);")){

            pstmt.executeUpdate();
            System.out.println("ClientList Table successfull created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createVehicleList(Connection con){
        try (PreparedStatement pstmt = con.prepareStatement
                ("CREATE TABLE IF NOT EXISTS VehicleList "
                    +"(id INTEGER, vehicleType TEXT, vehicleDesignation TEXT, manufacturer TEXT, power TEXT, salesPrice DOUBLE);")){

            pstmt.executeUpdate();
            System.out.println("VehicleList Table successfull created");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean existClient(Client client){
        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM  ClientList ;")) {

            ResultSet set = pstmt.executeQuery();
            while (set.next()) {
                if (set.getLong("id") == client.getId()
                        &&  set.getString("firstName").equals(client.getFirstName())
                        &&  set.getString("lastName").equals(client.getFirstName())
                        &&  set.getString("address").equals(client.getFirstName()))
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existVehicle(Vehicle vehicle){

        try (PreparedStatement pstmt = connection.prepareStatement(
                "SELECT * FROM  VehicleList ;")) {

            ResultSet set = pstmt.executeQuery();
            while (set.next()) {
                if (set.getLong("id") == vehicle.getId()
                        &&  set.getString("vehicleType").equals(vehicle.getVehicleType())
                        &&  set.getString("vehicleDesignation").equals(vehicle.getVehicleDesignation())
                        &&  set.getString("manufacturer").equals(vehicle.getManufacturer())
                        &&  set.getDouble("salesPrice") == vehicle.getSalesPrice()){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public Boolean openWritableClientList() {
        try {
            if (!connection.isClosed()){
                connection.close();
            }
            if (connection.isClosed()){
                connection = DriverManager.getConnection("jdbc:h2:file:D:/Datenbank");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean writeClient(Client client) {

        if (!existClient(client)){
            try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO ClientList (id, firstName, lastName, address) VALUES(?,?,?,?) ;")){
                pstmt.setLong(1, client.getId());
                pstmt.setString(2, client.getFirstName());
                pstmt.setString(3, client.getLastName());
                pstmt.setString(4, client.getAddress());
                pstmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;                // client is successfull inserted
        }

        return false;                   //client was already in the table
    }

    @Override
    public void writeClientList(ArrayList<Client> clients) {

        openWritableClientList();

        for (Client client: clients) {
            writeClient(client);
        }

        closeWritableClientList();

        System.out.println("Data sind successfull saved in Database ");
    }

    @Override
    public void closeWritableClientList() {

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean openReadableClientList() {
        try {
            if (!connection.isClosed()){
                connection.close();
            }

            if (connection.isClosed()){
                connection = DriverManager.getConnection("jdbc:h2:file:D:/Datenbank");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
    public ArrayList<Client> readClientList() {

        ArrayList<Client> clients = new ArrayList<>();
        openReadableClientList();

        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM ClientList ;")){
            ResultSet set = pstmt.executeQuery();

            while (set.next()){

                clients.add(readClient(set.getInt("id"),set.getString("firstName"),
                        set.getString("lastName"), set.getString("address")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeReadableClientList();
        return clients;
    }

    @Override
    public Boolean closeReadableClientList() {

        try {

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public Boolean openWritableVehicleList() {
        try {
            if (connection.isClosed()){
                connection = DriverManager.getConnection("jdbc:h2:file:D:/Datenbank");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean writeVehicle(Vehicle vehicle) {

        if (!existVehicle(vehicle)){
            try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO VehicleList "
                    +"(id, vehicleType, vehicleDesignation, manufacturer, power, salesPrice) VALUES(?,?,?,?,?,?);")){
                pstmt.setLong(1, vehicle.getId());
                pstmt.setString(2, vehicle.getVehicleType());
                pstmt.setString(3, vehicle.getVehicleDesignation());
                pstmt.setString(4, vehicle.getManufacturer());
                pstmt.setString(4, vehicle.getPower());
                pstmt.setDouble(4, vehicle.getSalesPrice());

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;                // vehicle is successfull inserted
        }

        return false;                   //vehicle was already in the table
    }

    @Override
    public void writeVehicleList(ArrayList<Vehicle> vehicles) {

        openWritableVehicleList();

        for (Vehicle vehicle: vehicles) {
            writeVehicle(vehicle);
        }

        closeWritableVehicleList();

        System.out.println("Data sind successfull saved in Database ");
    }

    @Override
    public void closeWritableVehicleList() {

        try {
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Boolean openReadableVehicleList() {
        try {
            if (connection.isClosed()){
                connection = DriverManager.getConnection("jdbc:h2:file:D:/Datenbank");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Vehicle readVehicle(int id, String vehicleType, String vehicleDesignation, String manufacturer, String power, double salesPrices) {

        Vehicle vehicle = new Vehicle();

        vehicle.setId(id);
        vehicle.setVehicleType(vehicleType);
        vehicle.setVehicleDesignation(vehicleDesignation);
        vehicle.setManufacturer(manufacturer);
        vehicle.setPower(power);
        vehicle.setSalesPrice(salesPrices);
        return vehicle;
    }

    @Override
    public ArrayList<Vehicle> readVehicleList() {

        ArrayList<Vehicle> vehicles = new ArrayList<>();
        openReadableVehicleList();

        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM VehicleList;")){

            ResultSet set = pstmt.executeQuery();
            while (set.next()){

                Vehicle vehicle = readVehicle(set.getInt("id"), set.getString("vehicleType"),
                        set.getString("vehicleDesignation"), set.getString("manufacturer"), set.getString("power"),
                        set.getDouble("salesPrices"));

                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeReadableVehicleList();
        return vehicles;
    }

    @Override
    public Boolean closeReadableVehicleList() {

        try {

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
