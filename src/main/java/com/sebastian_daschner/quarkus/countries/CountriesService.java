package com.sebastian_daschner.quarkus.countries;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/v3.1/all")
@RegisterRestClient(baseUri = "https://restcountries.com/")
public interface CountriesService {

    @GET
    List<Countries.Country> getCountries();

}
