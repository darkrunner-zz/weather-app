package com.jvs.owm.service;


import com.jvs.owm.model.Main;
import com.jvs.owm.model.ReadingList;
import com.jvs.owm.model.Response;
import com.jvs.owm.service.model.Statistics;
import com.jvs.owm.service.rest.WeatherServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class ForecastController {
    private WeatherServiceClient weatherServiceClient;

    public ForecastController(WeatherServiceClient weatherServiceClient) {
        this.weatherServiceClient = weatherServiceClient;
    }

    @RequestMapping("/forecast")
    public String forecast(@RequestParam(value="name", required=false, defaultValue="Sofia,bg") String name, Model model) {
        Response response = weatherServiceClient.getWeatherInfo(name);
        processResponse(model, response);
        return "forecast";
    }

    private void processResponse(Model model, Response response) {
        LinkedHashMap<String, Statistics> results = new LinkedHashMap<>();
        Double minTempForPeriod = null;
        Double maxTempForPeriod = null;

        for(ReadingList record : response.getList()) {

            Main main = record.getMain();
            String dateTxt = record.getDtTxt().substring(0, 10);
            Statistics statistics = results.get(dateTxt);
            if(statistics == null) {
                statistics = new Statistics();
                statistics.setMaxTemperature(main.getTempMax());
                statistics.setMinTemperature(main.getTempMin());
                results.put(dateTxt, statistics);
            } else {
                Double minTemperature = main.getTempMin();

                if(statistics.getMinTemperature() > minTemperature) {
                    statistics.setMinTemperature(minTemperature);
                }

                Double maxTemperature = main.getTempMax();

                if(statistics.getMaxTemperature() < maxTemperature) {
                    statistics.setMaxTemperature(maxTemperature);
                }

            }

            if(minTempForPeriod == null || minTempForPeriod > main.getTempMin()) {
                minTempForPeriod = main.getTempMin();
            }

            if(maxTempForPeriod == null || maxTempForPeriod < main.getTempMax()) {
                maxTempForPeriod = main.getTempMax();
            }
        }

        model.addAttribute("readings", results);
        model.addAttribute("max", maxTempForPeriod);
        model.addAttribute("min", minTempForPeriod);
        model.addAttribute("maxCel", maxTempForPeriod - 273.15);
        model.addAttribute("minCel", minTempForPeriod - 273.15);
        model.addAttribute("name", response.getCity().getName());
    }

    @RequestMapping("/forecast_cord")
    public String forecastByCords(@RequestParam(value="lat") Double latitude, @RequestParam(value="lon") Double longitude, Model model) {
        Response response = weatherServiceClient.getWeatherInfoByCords(latitude, longitude);
        processResponse(model, response);
        return "forecast";
    }

}