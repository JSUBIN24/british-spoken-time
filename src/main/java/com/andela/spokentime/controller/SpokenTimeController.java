package com.andela.spokentime.controller;

import com.andela.spokentime.dto.SpokenTimeRequest;
import com.andela.spokentime.dto.SpokenTimeResponse;
import com.andela.spokentime.service.SpokenTimeService;
import org.springframework.web.bind.annotation.*;

@RestController
public class SpokenTimeController {


    private final SpokenTimeService service;

    public SpokenTimeController(SpokenTimeService service) {
        this.service = service;
    }

    @PostMapping("/spoken-time")
    public SpokenTimeResponse getSpokenTime(@RequestBody SpokenTimeRequest spokenTimeRequest) {
        return service.getSpokenTime(spokenTimeRequest.time(), spokenTimeRequest.locale());
    }
}
