package id.maulana.apps.weather.controller;

import co.elastic.apm.api.CaptureTransaction;
import id.maulana.apps.weather.dto.response.CurrentWeatherResponseDTO;
import id.maulana.apps.weather.exception.ValidationException;
import id.maulana.apps.weather.service.WeatherService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.security.SecuritySchemes;

//import javax.inject.Inject;

@Path("/v1/weather/info")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecuritySchemes(value = {
        @SecurityScheme(securitySchemeName = "accessToken", type = SecuritySchemeType.HTTP, scheme = "bearer", apiKeyName = "Authorization: Bearer", bearerFormat = "jwt")})
public class WeatherController {

    private final WeatherService weatherService;

    @Inject
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GET
    @Path("/current-weather/{city}")
    @CaptureTransaction
    @Operation(summary = "Get Current Weather Information by City", description = "Get Current Weather Information by City")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
            @APIResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
            @APIResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_JSON)),})
    public Response getCurrentWeatherByCity(@PathParam("city") String city) throws ValidationException {
        CurrentWeatherResponseDTO response = weatherService.currentWeatherByCity(city);
        return Response.ok().entity(response).build();
    }

}
