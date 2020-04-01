package pl.mzuchnik.springpracadomowa5b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mzuchnik.springpracadomowa5b.model.WeatherModel;
import pl.mzuchnik.springpracadomowa5b.service.WeatherService;

import java.util.Optional;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }


    @GetMapping
    public String getMainPage(@RequestParam(value = "cityName", required = false, defaultValue = "Warsaw") String city, Model model) {
        Optional<WeatherModel> weatherInfo = weatherService.getWeatherInfo(city);
        weatherInfo.ifPresent(weatherModel -> model.addAttribute("weatherForCity", weatherModel));
        model.addAttribute("cityName", city);
        return "index";
    }

    @PostMapping
    public String getCityName(@RequestParam(name = "cityName") String city, RedirectAttributes ra) {
        System.out.println("Miasto" + city);
        ra.addAttribute("cityName", city);
        return "redirect:/weather";
    }
}
