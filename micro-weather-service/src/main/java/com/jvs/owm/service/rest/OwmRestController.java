package com.jvs.owm.service.rest;

import com.jvs.owm.model.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class OwmRestController {

    private final OwmClient owmClient;

    public OwmRestController(OwmClient owmClient) {
        this.owmClient = owmClient;
    }

    @RequestMapping("/forecast")
    public Response getForecast(@RequestParam(value="name", required=false, defaultValue="Sofia,bg") String name) {
        return owmClient.getInfo(name);
    }

    @RequestMapping("/forecast_cords")
    public Response getForecastByCords(@RequestParam(value="lat") Double latitude,
                                       @RequestParam(value="lon") Double longitude) {
        return owmClient.getInfoCords(latitude, longitude);
    }
}
