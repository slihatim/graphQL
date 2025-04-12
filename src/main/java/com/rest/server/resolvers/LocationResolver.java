package com.rest.server.resolvers;

import com.rest.server.models.Location;
import com.rest.server.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LocationResolver {
    @Autowired
    private LocationService locationService;

    @QueryMapping
    public Location singleLocation(@Argument String locationId){
        return locationService.singleLocation(locationId).get();
    }

    @QueryMapping
    public List<Location> allLocations(@Argument int page, @Argument int limit){
        Page<Location> pageResult = locationService.allLocations(PageRequest.of(page, limit));
        return pageResult.getContent();
    }

    @MutationMapping
    public Location createLocation(@Argument String locationStreet, @Argument String locationCity, @Argument String locationState, @Argument String locationCountry, @Argument String locationTimezone){
        Location l = new Location();
        l.setLocationStreet(locationStreet);
        l.setLocationCity(locationCity);
        l.setLocationState(locationState);
        l.setLocationCountry(locationCountry);
        l.setLocationTimezone(locationTimezone);
        return locationService.createLocation(l);
    }

    @MutationMapping
    public Location updateLocation(@Argument String locationId, @Argument String locationStreet, @Argument String locationCity, @Argument String locationState, @Argument String locationCountry, @Argument String locationTimezone){
        Location l = new Location();
        l.setLocationStreet(locationStreet);
        l.setLocationCity(locationCity);
        l.setLocationState(locationState);
        l.setLocationCountry(locationCountry);
        l.setLocationTimezone(locationTimezone);
        return locationService.updateLocation(locationId, l);
    }

    @MutationMapping
    public String deleteLocation(@Argument String locationId){
        locationService.deleteLocation(locationId);
        return locationId;
    }
}
