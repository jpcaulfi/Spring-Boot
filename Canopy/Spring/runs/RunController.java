package com.canopy.canopyspring.runs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class RunController {

    @Autowired
    public RunRepository runRepository;

    @GetMapping("runs")
    public List<Run> getAllRuns() {
        return runRepository.findAll();
    }
}
