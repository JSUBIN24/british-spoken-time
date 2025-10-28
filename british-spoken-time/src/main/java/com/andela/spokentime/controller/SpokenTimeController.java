package com.andela.spokentime.controller;

import com.andela.spokentime.dto.SpokenTimeResponse;
import com.andela.spokentime.service.SpokenTimeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpokenTimeController   {


    private final SpokenTimeService service;

    public SpokenTimeController(SpokenTimeService service) {
        this.service = service;
    }

    @GetMapping("/spoken-time")
    public SpokenTimeResponse getSpokenTime(@RequestParam String time, @RequestParam(defaultValue = "british") String locale){
        return service.getSpokenTime(time,locale);
    }
}
