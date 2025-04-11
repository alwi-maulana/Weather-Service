package id.maulana.apps.weather.service;

import id.maulana.apps.weather.dto.response.CurrentWeatherDTO;
import id.maulana.apps.weather.dto.response.CurrentWeatherResponseDTO;
import id.maulana.apps.weather.entity.WeatherDataHistory;
import id.maulana.apps.weather.exception.ValidationException;
import id.maulana.apps.weather.repository.WeatherDataHistoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import javax.inject.Inject;

import java.sql.Timestamp;
import java.time.Instant;

import static id.maulana.apps.weather.common.CommonConstant.MessageError.FAILED_FETCH_DATA_CURRENT_WEATHER;

@ApplicationScoped
public class WeatherServiceImpl implements WeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);

    private final ThirdPartiesService thirdPartiesService;
    private final WeatherDataHistoryRepository weatherDataHistoryRepository;

    @Inject
    public WeatherServiceImpl(ThirdPartiesService thirdPartiesService, WeatherDataHistoryRepository weatherDataHistoryRepository) {
        this.thirdPartiesService = thirdPartiesService;
        this.weatherDataHistoryRepository = weatherDataHistoryRepository;
    }

    @Override
    @Transactional
    public CurrentWeatherResponseDTO  currentWeatherByCity(String city) throws ValidationException {
        CurrentWeatherDTO currentWeatherDTO = thirdPartiesService.getCurrentWeather(city);

        if (currentWeatherDTO == null) {
            LOGGER.error("Error get current weather: {}", FAILED_FETCH_DATA_CURRENT_WEATHER);
            throw new ValidationException(FAILED_FETCH_DATA_CURRENT_WEATHER);
        }

        WeatherDataHistory weatherDataHistory = new WeatherDataHistory();
        weatherDataHistory.setCityName(currentWeatherDTO.getLocation().getName());
        weatherDataHistory.setTemperature(currentWeatherDTO.getCurrent().getTempC());
        weatherDataHistory.setHumidity(currentWeatherDTO.getCurrent().getHumidity());
        weatherDataHistory.setWeatherDescription(currentWeatherDTO.getCurrent().getCondition().getText());
        weatherDataHistory.setFetchedAt(Timestamp.from(Instant.now()));
        weatherDataHistoryRepository.persist(weatherDataHistory);

        return new CurrentWeatherResponseDTO(currentWeatherDTO);
    }
}
