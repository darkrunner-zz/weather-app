package com.jvs.owm.service.rest;


import com.jvs.owm.model.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "owm", url = "http://api.openweathermap.org/data/2.5")
public interface OwmClient {

    @RequestMapping(method = RequestMethod.GET, value = "/forecast/?q={q}&APPID=${APPID}")
    Response getInfo(@PathVariable("q") String country);

    @RequestMapping(method = RequestMethod.GET, value = "/forecast/?lat={lat}&lon={lon}&APPID=${APPID}")
    Response getInfoCords(@PathVariable("lat") Double latitude, @PathVariable("lon") Double longitude);

}
