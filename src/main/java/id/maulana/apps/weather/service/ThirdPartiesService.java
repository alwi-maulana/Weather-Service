package id.maulana.apps.weather.service;

import id.maulana.apps.weather.dto.response.CurrentWeatherDTO;
import id.maulana.apps.weather.exception.ValidationException;
import id.maulana.apps.weather.util.RestUtil;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import kong.unirest.HttpResponse;
import kong.unirest.JsonObjectMapper;
import org.eclipse.microprofile.config.inject.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.*;

import static id.maulana.apps.weather.common.CommonConstant.MessageError.FAILED_FETCH_DATA_CURRENT_WEATHER;

@ApplicationScoped
public class ThirdPartiesService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThirdPartiesService.class);

    @ConfigProperty(name = "api.weather.key")
    String weatherApiKey;

    @ConfigProperty(name = "weather.current.endpoint")
    String weatherCurrentEndpoint;


    public CurrentWeatherDTO getCurrentWeather(String city) throws ValidationException {
        String url = weatherCurrentEndpoint + "key=" + weatherApiKey + "&q=" + city;
        LOGGER.info("Get current weather URL: {}", url);
        HttpResponse<Object> response = RestUtil.restTemplateGetObject(url);

        if (response.getStatus() != 200 || !response.isSuccess()) {
            LOGGER.error("Error get current weather: {}", FAILED_FETCH_DATA_CURRENT_WEATHER);
            throw new ValidationException(FAILED_FETCH_DATA_CURRENT_WEATHER);
        }

        JsonObject jsonResponse = JsonObject.mapFrom(response.getBody());

        if (jsonResponse.isEmpty()) {
            LOGGER.error("Error get current weather: {}", FAILED_FETCH_DATA_CURRENT_WEATHER);
            throw new ValidationException(FAILED_FETCH_DATA_CURRENT_WEATHER);
        }

        return jsonResponse.mapTo(CurrentWeatherDTO.class);
    }
}
