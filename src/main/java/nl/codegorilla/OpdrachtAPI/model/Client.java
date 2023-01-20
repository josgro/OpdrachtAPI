package nl.codegorilla.OpdrachtAPI.model;

import org.springframework.data.annotation.Id;

public class Client {

    @Id
    private int id;

    private String firstName;

    private String streetName;

    private int houseNumber;

    private String houseNumberSuffix;

    private String postalCode;

    private String phoneNumber;

    public Client() {}

    public Client(int id, String firstName, String streetName, int houseNumber, String houseNumberSuffix, String postalCode, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.houseNumberSuffix = houseNumberSuffix;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getHouseNumberSuffix() {
        return houseNumberSuffix;
    }

    public void setHouseNumberSuffix(String houseNumberSuffix) {
        this.houseNumberSuffix = houseNumberSuffix;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", houseNumber=" + houseNumber +
                ", houseNumberSuffix='" + houseNumberSuffix + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
