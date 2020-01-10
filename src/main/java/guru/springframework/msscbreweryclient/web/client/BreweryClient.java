package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
@Slf4j
public class BreweryClient {
    public final String BEER_PATH_V1 = "/api/v1/beer/";
    private final RestTemplate restTemplate;
    private String apiHost;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public BeerDto getBeerById(UUID uuid) {
        log.info("Beer UUID {}", uuid.toString());
        return restTemplate.getForObject(apiHost + BEER_PATH_V1 + uuid.toString(), BeerDto.class);
    }

    public URI saveNewBeer(BeerDto dto) {
        return restTemplate.postForLocation(apiHost + BEER_PATH_V1, dto);
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public void updateBeer(UUID uuid, BeerDto dto) {
        restTemplate.put(apiHost + BEER_PATH_V1 + uuid.toString(), dto);
    }

    public void deleteBeer(UUID uuid) {
        restTemplate.delete(apiHost + BEER_PATH_V1 + uuid.toString());
    }
}
