package com.canopy.canopyspring.drivers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class DriverController {

    @Autowired
    public DriverRepository driverRepository;

    @GetMapping("drivers")
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }
}
