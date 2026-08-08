package com.example.demo.dto;

import java.util.List;

public class HotelDetailsDto {
    private Long id;
    private String name;
    private String description;
    private String brand;
    private AddressDto address;
    private ContactsDto contacts;
    private ArrivalTimeDto arrivalTime;
    private List<String> amenities;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public List<String> getAmenities() { return amenities; }
    public void setAmenities(List<String> amenities) { this.amenities = amenities; }
    public AddressDto getAddress() { return address; }
    public void setAddress(AddressDto address) { this.address = address; }
    public ContactsDto getContacts() { return contacts; }
    public void setContacts(ContactsDto contacts) { this.contacts = contacts; }
    public ArrivalTimeDto getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(ArrivalTimeDto arrivalTime) { this.arrivalTime = arrivalTime; }

    public static class AddressDto {
        private String houseNumber;
        private String street;
        private String city;
        private String country;
        private String postCode;

        public void setHouseNumber(String houseNumber) { this.houseNumber = houseNumber; }
        public void setStreet(String street) { this.street = street; }
        public void setCity(String city) { this.city = city; }
        public void setCountry(String country) { this.country = country; }
        public void setPostCode(String postCode) { this.postCode = postCode; }
        public String getCity() { return city; }
    }

    public static class ContactsDto {
        private String phone;
        private String email;
        public void setPhone(String phone) { this.phone = phone; }
        public void setEmail(String email) { this.email = email; }
    }

    public static class ArrivalTimeDto {
        private String checkIn;
        private String checkOut;
        public void setCheckIn(String checkIn) { this.checkIn = checkIn; }
        public void setCheckOut(String checkOut) { this.checkOut = checkOut; }
    }
}