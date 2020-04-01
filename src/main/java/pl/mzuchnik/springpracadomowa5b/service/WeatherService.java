package pl.mzuchnik.springpracadomowa5b.service;

import pl.mzuchnik.springpracadomowa5b.model.WeatherModel;

import java.util.Optional;

public interface WeatherService {

    Optional<WeatherModel> getWeatherInfo(String city);

    String getApiSrc();
}
