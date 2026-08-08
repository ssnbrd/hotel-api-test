package com.example.demo.controller;

import com.example.demo.dto.HotelDetailsDto;
import com.example.demo.dto.HotelShortDto;
import com.example.demo.entity.Hotel;
import com.example.demo.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<HotelShortDto> getAll() {
        return hotelService.getAllHotels();
    }

    @PostMapping
    public HotelShortDto create(@RequestBody Hotel hotel) {
        Hotel saved = hotelService.createHotel(hotel);

        return new HotelShortDto(saved.getId(), saved.getName(), saved.getDescription(), "address", saved.getPhone());
    }

    @GetMapping("/search")
    public List<HotelShortDto> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country) {
        return hotelService.searchHotels(name, brand, city, country);
    }
    @GetMapping("/{id}")
    public HotelDetailsDto getDetails(@PathVariable Long id) {
        return hotelService.getHotelDetails(id);
    }

    @GetMapping("/histogram/{param}")
    public Map<String, Long> getHistogram(@PathVariable String param) {
        return hotelService.getHistogram(param);
    }
}
