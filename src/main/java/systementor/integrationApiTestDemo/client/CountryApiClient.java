package systementor.integrationApiTestDemo.client;
import systementor.integrationApiTestDemo.model.Country;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CountryApiClient {

    private static final String BASE_URL = "https://restcountries.com/v3.1";

    private final WebClient webClient;

    public CountryApiClient() {
        this.webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }

    public Country[] fetchAllCountryNames() {
        return webClient.get()
                .uri("/all?fields=name")
                .retrieve()
                .bodyToMono(Country[].class)
                .block();
    }

    public Country[] fetchCountryByName(String name) {
        return webClient.get()
                .uri("/name/{name}", name)
                .retrieve()
                .bodyToMono(Country[].class)
                .block();
    }
}