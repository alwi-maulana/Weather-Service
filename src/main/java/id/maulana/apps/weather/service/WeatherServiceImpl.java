package id.maulana.apps.weather.service;

import id.maulana.apps.weather.dto.response.CurrentWeatherDTO;
import id.maulana.apps.weather.dto.response.CurrentWeatherResponseDTO;
import id.maulana.apps.weather.exception.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import javax.inject.Inject;

import static id.maulana.apps.weather.common.CommonConstant.MessageError.FAILED_FETCH_DATA_CURRENT_WEATHER;

@ApplicationScoped
public class WeatherServiceImpl implements WeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);

    private final ThirdPartiesService thirdPartiesService;

    @Inject
    public WeatherServiceImpl(ThirdPartiesService thirdPartiesService) {
        this.thirdPartiesService = thirdPartiesService;
    }

    @Override
    public CurrentWeatherResponseDTO  currentWeatherByCity(String city) throws ValidationException {
        CurrentWeatherDTO currentWeatherDTO = thirdPartiesService.getCurrentWeather(city);

        if (currentWeatherDTO == null) {
            LOGGER.error("Error get current weather: {}", FAILED_FETCH_DATA_CURRENT_WEATHER);
            throw new ValidationException(FAILED_FETCH_DATA_CURRENT_WEATHER);
        }

        return new CurrentWeatherResponseDTO(currentWeatherDTO);
    }
}
