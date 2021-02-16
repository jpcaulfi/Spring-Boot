package com.canopy.canopyspring.bids;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class BidController {

    @Autowired
    public BidRepository bidRepository;

    @GetMapping("bids")
    public List<Bid> getAllBids() {
        return bidRepository.findAll();
    }

    @PostMapping("bids")
    Bid insertBid(@RequestBody Bid bid) {
        return bidRepository.save(bid);
    }
}
