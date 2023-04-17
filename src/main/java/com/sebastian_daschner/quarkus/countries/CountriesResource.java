package com.sebastian_daschner.quarkus.countries;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("countries")
@Produces(MediaType.APPLICATION_JSON)
public class CountriesResource {

    @Inject
    Countries countries;

    @GET
    public List<String> countries() {
        return countries.getCountryNames();
    }

}
