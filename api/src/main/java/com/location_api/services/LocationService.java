package com.location_api.services;

import com.location_api.helpers.GeolocationCalculator;
import com.location_api.models.Location;
import com.location_api.repositories.LocationRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LocationService implements ILocationService {
    private LocationRepository _repository;

    @Autowired
    public LocationService(LocationRepository repository) {
        _repository = repository;
    }

    /**
     * Creates location / saves location to data layer.
     * @param location an object to be saved.
     * @return object that was saved.
     */
    public Location createLocation(Location location) {
        location.setId(ObjectId.get());
        _repository.save(location);
        return location;
    }

    /**
     * Updates the Location object on data layer.
     * @param location updated object.
     */
    public void modifyLocation(Location location) {
        _repository.save(location);
    }

    /**
     * Deletes Location from data layer by id.
     * @param id of a location to delete.
     */
    public void deleteLocation(ObjectId id) {
        _repository.delete(_repository.findById(id));
    }

    /**
     *
     * @return all locations on data layer.
     */
    public List<Location> getAll() {
        return _repository.findAll();
    }

    /**
     * Returns Location object by id from data layer.
     * @param id of the location.
     * @return location object.
     */
    public Location getById(ObjectId id) {
        return _repository.findById(id);
    }

    /**
     * Finds all locations on data layer which are at maximum given distance from given location.
     * @param id of a reference Location.
     * @param radius maximum distance.
     * @return list of locations whose distance from reference Location is smaller or equal to given radius.
     */
    public List<Location> getFilteredLocations(ObjectId id, Double radius) {
        Location loc = _repository.findById(id);
        if (null == loc) {
            return new ArrayList<>();
        }
        double[] boundaries = GeolocationCalculator.getBoundaries(loc.getLat(), loc.getLng(), radius);
        Location[] locations = _repository.findByLatBetweenAndLngBetween(boundaries[0], boundaries[1], boundaries[2], boundaries[3]);
        return Arrays.asList(locations);
    }
}
