package com.canopy.canopyspring.rescues;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class RescueController {

    @Autowired
    public RescueRepository rescueRepository;

    @GetMapping("rescues")
    public List<Rescue> getAllRescues() {
        return rescueRepository.findAll();
    }

    @PostMapping("rescues")
    Rescue insertRescue(@RequestBody Rescue rescue) {
        return rescueRepository.save(rescue);
    }
}
