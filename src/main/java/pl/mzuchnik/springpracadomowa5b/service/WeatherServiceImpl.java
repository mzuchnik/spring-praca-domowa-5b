package pl.mzuchnik.springpracadomowa5b.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.mzuchnik.springpracadomowa5b.model.Parent;
import pl.mzuchnik.springpracadomowa5b.model.WeatherModel;

import java.util.Optional;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Value("${api-weather-url}")
    public String weatherApi;

    private RestTemplate restTemplate;

    public WeatherServiceImpl() {
        restTemplate = new RestTemplate();
    }

    @Override
    public Optional<WeatherModel> getWeatherInfo(String city) {
        WeatherModel forObject = restTemplate.getForObject(weatherApi + "/" + getCityID(city), WeatherModel.class);
        return Optional.ofNullable(forObject);
    }

    private Integer getCityID(String city){
        ResponseEntity<Parent[]> myParentResponse = restTemplate.exchange(
                weatherApi + "/search/?query="+city,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Parent[].class);
        return myParentResponse.getBody()[0].getWoeid();
    }

   /* @EventListener(ApplicationReadyEvent.class)
    public void showCityId()
    {
        System.out.println(getCityID("London"));
    }*/

    @Override
    public String getApiSrc() {
        return weatherApi;
    }
}
