package others;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Santiago Ventura <santiago.r.ventura at gmail.com>
 */
public class Address {
    private static String ADDRESS_TEXT_PATTERN = "STREET:([\\w\\s]+.?)\\s+NUMBER:([\\w\\s]+?)\\s+ZIP:([\\w\\s]+.?)\\s+CITY:([\\w\\s]+.?)\\s+COUNTRY:([\\w\\s]+.?)";

    private String street;
    private String streetNumber;
    private String zip;
    private String city;
    private String country;

    public Address() {
    }

    public Address(String street, String streetNumber, String zip, String city, String country) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.zip = zip;
        this.city = city;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String number) {
        this.streetNumber = number;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static Address parse(String text) {
        Address address = new Address();
        Pattern addressPattern = Pattern.compile(ADDRESS_TEXT_PATTERN, Pattern.MULTILINE);
        Matcher matcher = addressPattern.matcher(text);
        if (matcher.find()) {
            address.setStreet(matcher.group(1));
            address.setStreetNumber(matcher.group(2));
            address.setZip(matcher.group(3));
            address.setCity(matcher.group(4));
            address.setCountry(matcher.group(5));
        }
        return address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", zip='" + zip + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public static void main(String[] args) {
        String textAddress = "STREET:BUHROWSTR NUMBER:10 ZIP:12167 CITY:BERLIN COUNTRY:GERMANY";
        Address address = Address.parse(textAddress);
        System.out.println("address = " + address);
    }
}