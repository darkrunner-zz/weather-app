package com.jvs.owm.service.rest;

import com.jvs.owm.model.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "owm-service")
public interface WeatherServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/weather/forecast/?name={q}")
    Response getWeatherInfo(@PathVariable("q") String country);

    @RequestMapping(method = RequestMethod.GET, value = "/weather/forecast/?lat={lat}&lon={lon}")
    Response getWeatherInfoByCords(@PathVariable("lat") Double latitude, @PathVariable("lon") Double longitude);

}
