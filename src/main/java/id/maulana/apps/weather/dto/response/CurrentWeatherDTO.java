package id.maulana.apps.weather.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherDTO implements Serializable {
    private LocationDTO location;
    private CurrentDTO current;

    public CurrentWeatherDTO(LocationDTO location, CurrentDTO current) {
        this.location = location;
        this.current = current;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public CurrentDTO getCurrent() {
        return current;
    }

    public void setCurrent(CurrentDTO current) {
        this.current = current;
    }
}
