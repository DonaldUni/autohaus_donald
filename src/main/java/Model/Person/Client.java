package Model.Person;

public class Client{

    private static long id = -1;

    private String firstName;

    private String lastName;

    private String address;

    public Client() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
