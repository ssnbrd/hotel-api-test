package com.example.demo.service;

import com.example.demo.dto.HotelDetailsDto;
import com.example.demo.dto.HotelShortDto;
import com.example.demo.entity.Hotel;
import com.example.demo.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    private HotelShortDto convertToShortDto(Hotel hotel) {
        String fullAddress = String.format("%s %s, %s, %s, %s",
                hotel.getHouseNumber(), hotel.getStreet(), hotel.getCity(), hotel.getPostCode(), hotel.getCountry());

        return new HotelShortDto(
                hotel.getId(),
                hotel.getName(),
                hotel.getDescription(),
                fullAddress,
                hotel.getPhone()
        );
    }

    public List<HotelShortDto> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(this::convertToShortDto)
                .collect(Collectors.toList());
    }

    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public List<HotelShortDto> searchHotels(String name, String brand, String city, String country) {
        return hotelRepository.findByNameContainingIgnoreCaseOrBrandContainingIgnoreCaseOrCityContainingIgnoreCaseOrCountryContainingIgnoreCase(
                        name, brand, city, country).stream()
                .map(this::convertToShortDto)
                .collect(Collectors.toList());
    }
    public HotelDetailsDto getHotelDetails(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        HotelDetailsDto dto = new HotelDetailsDto();
        dto.setId(hotel.getId());
        dto.setName(hotel.getName());
        dto.setDescription(hotel.getDescription());
        dto.setBrand(hotel.getBrand());
        dto.setAmenities(hotel.getAmenities());

        HotelDetailsDto.AddressDto address = new HotelDetailsDto.AddressDto();
        address.setHouseNumber(hotel.getHouseNumber());
        address.setStreet(hotel.getStreet());
        address.setCity(hotel.getCity());
        address.setCountry(hotel.getCountry());
        address.setPostCode(hotel.getPostCode());
        dto.setAddress(address);

        HotelDetailsDto.ContactsDto contacts = new HotelDetailsDto.ContactsDto();
        contacts.setPhone(hotel.getPhone());
        contacts.setEmail(hotel.getEmail());
        dto.setContacts(contacts);

        HotelDetailsDto.ArrivalTimeDto arrival = new HotelDetailsDto.ArrivalTimeDto();
        arrival.setCheckIn(hotel.getCheckIn());
        arrival.setCheckOut(hotel.getCheckOut());
        dto.setArrivalTime(arrival);

        return dto;
    }

    public Map<String, Long> getHistogram(String param) {
        List<Hotel> hotels = hotelRepository.findAll();
        switch (param.toLowerCase()) {
            case "brand":
                return hotels.stream().collect(Collectors.groupingBy(Hotel::getBrand, Collectors.counting()));
            case "city":
                return hotels.stream().collect(Collectors.groupingBy(Hotel::getCity, Collectors.counting()));
            case "country":
                return hotels.stream().collect(Collectors.groupingBy(Hotel::getCountry, Collectors.counting()));
            case "amenities":
                return hotels.stream()
                        .flatMap(h -> h.getAmenities().stream())
                        .collect(Collectors.groupingBy(a -> a, Collectors.counting()));
            default:
                return Map.of();
        }

    }
}