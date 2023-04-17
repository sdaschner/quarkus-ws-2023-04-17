package com.sebastian_daschner.quarkus;

import com.sebastian_daschner.quarkus.countries.Countries;
import com.sebastian_daschner.quarkus.countries.CountriesService;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Path("concurrent")
public class ConcurrentResource {

    @Inject
    ManagedExecutor managedExecutor;

    @Inject
    @RestClient
    CountriesService countriesService;

    @Inject
    Countries countries;

    @GET
    public String concurrent() {
//        CompletableFuture<List<Countries.Country>> countries = managedExecutor
//                .supplyAsync(() -> countriesService.getCountries());
//
//        countries
//                .thenApply(countries1 -> countries1.stream().map(c -> c.name.common).collect(Collectors.toList()))
//                .thenAccept(System.out::println);


        countries.getCountryNamesAsync()
                .thenAccept(System.out::println);

        return "OK";
    }

}
