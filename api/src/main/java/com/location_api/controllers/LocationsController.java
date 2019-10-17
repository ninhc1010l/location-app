package com.location_api.controllers;

import com.location_api.models.Location;
import com.location_api.services.LocationService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class LocationsController {
    private LocationService _locationService;

    @Autowired
    public LocationsController(LocationService locationService) {
        _locationService = locationService;
    }

    @RequestMapping(value = "/location", method = RequestMethod.POST)
    public Location addLocation(@Valid @RequestBody Location location) {
        return _locationService.createLocation(location);
    }

    @RequestMapping(value = "/location", method = RequestMethod.PUT)
    public void modify(@Valid @RequestBody Location location) {
        _locationService.modifyLocation(location);
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.DELETE)
    public void deleteLocation(@PathVariable ObjectId id) {
        _locationService.deleteLocation(id);
    }

    @RequestMapping(value = "/location", method = RequestMethod.GET)
    public List<Location> getAllLocations() {
        return _locationService.getAll();
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.GET)
    public Location getSpecificLocation(@PathVariable("id") ObjectId id) {
        return _locationService.getById(id);
    }

    @RequestMapping(value = "/location/filtered", method = RequestMethod.GET)
    public List<Location> getFiltered(@RequestParam ObjectId addressId, @RequestParam Double radius) {
        return _locationService.getFilteredLocations(addressId, radius);
    }
}
