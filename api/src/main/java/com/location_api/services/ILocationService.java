package com.location_api.services;

import com.location_api.models.Location;
import org.bson.types.ObjectId;

import java.util.List;

public interface ILocationService {
    Location createLocation(Location location);

    void modifyLocation(Location location);

    void deleteLocation(ObjectId id);

    List<Location> getAll();

    Location getById(ObjectId id);

    List<Location> getFilteredLocations(ObjectId id, Double radius);
}
