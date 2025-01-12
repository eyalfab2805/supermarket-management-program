package TomBitran_Eyal_Fabian;

public class Address {
    public String streetName;
    public String city;
    public String state;
    public int appNumber;

    // Constructor to initialize the address fields
    public Address(String street, String city, String state, int number) {
        this.streetName = street;
        this.city = city;
        this.state = state;
        this.appNumber = number;
    }

    // Returns the street name
    public String getStreetName() {
        return streetName;
    }

    // Returns the city
    public String getCity() {
        return city;
    }

    // Returns the state
    public String getState() {
        return state;
    }

    // Returns the apartment number
    public int getAppNumber() {
        return appNumber;
    }

    // Creates a string representation of the address
    public String addressToString() {
        StringBuilder addressDetails = new StringBuilder();
        addressDetails.append("Street name: ").append(this.getStreetName()).append(", ");
        addressDetails.append("City: ").append(this.getCity()).append(", ");
        addressDetails.append("State: ").append(this.getState()).append(", ");
        addressDetails.append("Apartment number: ").append(this.getAppNumber()).append("\n");
        return addressDetails.toString();
    }
}