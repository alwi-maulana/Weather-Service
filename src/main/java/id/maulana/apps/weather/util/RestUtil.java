package id.maulana.apps.weather.util;

import com.fasterxml.jackson.databind.util.ObjectBuffer;
import jakarta.enterprise.context.ApplicationScoped;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;


@ApplicationScoped
public class RestUtil {

    public static HttpResponse<Object> restTemplateGetObject(String url) {
        try {
            Unirest.config().verifySsl(false);

            return Unirest.get(url)
                    .asObject(Object.class);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
