package com.example.demo.dto;

public class HotelShortDto {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String phone;

    public HotelShortDto(Long id, String name, String description, String address, String phone) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.phone = phone;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
}