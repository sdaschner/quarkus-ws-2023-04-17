package com.sebastian_daschner.quarkus.countries;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@ApplicationScoped
public class Countries {

    private Client client;
    private WebTarget countriesTarget;

    @PostConstruct
    void initClient() {
        client = ClientBuilder.newBuilder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
        countriesTarget = client.target("https://restcountries.com/v3.1/all");
    }

    public List<String> getCountryNames() {
        JsonArray array = countriesTarget.request(MediaType.APPLICATION_JSON_TYPE)
                .get(JsonArray.class);

        return array.getValuesAs(JsonObject.class).stream()
                .map(o -> o.getJsonObject("name").getString("common"))
                .collect(Collectors.toList());
    }

    @PreDestroy
    void closeClient() {
        client.close();
    }

}
