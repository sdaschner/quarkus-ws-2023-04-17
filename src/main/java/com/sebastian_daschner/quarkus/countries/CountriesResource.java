package com.sebastian_daschner.quarkus.countries;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("countries")
@Produces(MediaType.APPLICATION_JSON)
public class CountriesResource {

    @Inject
    Countries countries;

    @Inject
    @RestClient
    CountriesService countriesService;

    @GET
    public List<String> countries() {
        return countries.getCountryNames();
    }

    @GET
    @Path("rest-client")
    public List<String> countriesRestClient() {
        return countriesService.getCountries().stream()
                .map(c -> c.name.common)
                .collect(Collectors.toList());
    }

}
