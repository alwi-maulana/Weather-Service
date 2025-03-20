package id.maulana.apps.weather.service;

import id.maulana.apps.weather.dto.response.CurrentWeatherResponseDTO;
import id.maulana.apps.weather.exception.ValidationException;

public interface WeatherService {
    CurrentWeatherResponseDTO currentWeatherByCity(String city) throws ValidationException;
}
