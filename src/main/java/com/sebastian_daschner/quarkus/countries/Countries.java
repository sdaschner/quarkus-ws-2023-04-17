package com.sebastian_daschner.quarkus.countries;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;
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

    @Retry
    @Fallback(fallbackMethod = "defaultCountryNames")
    @Timeout(value = 200)
    public List<String> getCountryNames() {
        GenericType<List<Country>> responseType = new GenericType<>(){};

        List<Country> countries = countriesTarget.request(MediaType.APPLICATION_JSON_TYPE)
                .get(responseType);

        return countries.stream().map(c -> c.name.common).collect(Collectors.toList());

//        return array.getValuesAs(JsonObject.class).stream()
//                .map(o -> o.getJsonObject("name").getString("common"))
//                .collect(Collectors.toList());
    }

    public CompletionStage<List<String>> getCountryNamesAsync() {
        GenericType<List<Country>> responseType = new GenericType<>(){};

        CompletionStage<List<Country>> countries = countriesTarget.request(MediaType.APPLICATION_JSON_TYPE)
                .rx()
                .get(responseType);

        return countries.thenApply(cs -> cs.stream().map(c -> c.name.common).collect(Collectors.toList()));
    }

    public List<String> defaultCountryNames() {
        return List.of("Germany", "Austria", "Italy");
    }

    @PreDestroy
    void closeClient() {
        client.close();
    }

    public static class Country {
        public CountryName name;
        public static class CountryName {
            public String common;
        }
    }

}
