package id.maulana.apps.weather.repository;

import id.maulana.apps.weather.entity.WeatherDataHistory;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WeatherDataHistoryRepository implements PanacheRepository<WeatherDataHistory> {
}
