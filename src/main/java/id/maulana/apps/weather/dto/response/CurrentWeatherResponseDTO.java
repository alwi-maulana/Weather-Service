package id.maulana.apps.weather.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherResponseDTO implements Serializable {
    private String location;
    private String localTime;
    private String temperature;
    private String condition;
    private String wind;
    private String humidity;
    private String pressure;

    public CurrentWeatherResponseDTO(CurrentWeatherDTO weather) {
        this.location = "📍 " + weather.getLocation().getName() + ", " + weather.getLocation().getCountry();
        this.localTime = "⏰ " + weather.getLocation().getLocaltime();
        this.temperature = "🌡️ " + weather.getCurrent().getTempC() + "°C (Feels like " + weather.getCurrent().getFeelslikeC() + "°C)";

        String conditionEmoji = switch (weather.getCurrent().getCondition().getCode()) {
            case 1000 -> "☀️"; // Sunny
            case 1003 -> "⛅"; // Partly Cloudy
            case 1006, 1009 -> "☁️"; // Cloudy
            case 1030, 1135, 1147 -> "🌫️"; // Foggy
            case 1063, 1180, 1183, 1240 -> "🌧️"; // Light Rain
            case 1087, 1276 -> "⛈️"; // Thunderstorm
            case 1210, 1213, 1255 -> "❄️"; // Snow
            default -> "🌥️"; // Default
        };

        this.condition = "🌥️ " + weather.getCurrent().getCondition().getText();
        this.wind = "💨 " + weather.getCurrent().getWindKph() + " km/h " + weather.getCurrent().getWindDir();
        this.humidity = "💦 " + weather.getCurrent().getHumidity() + "%";
        this.pressure = "🔵 " + weather.getCurrent().getPressureMb() + " mb";
    }

    public CurrentWeatherResponseDTO() {

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocalTime() {
        return localTime;
    }

    public void setLocalTime(String localTime) {
        this.localTime = localTime;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }
}
