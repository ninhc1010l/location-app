package com.location_api.repositories;

import com.location_api.models.Location;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepository extends MongoRepository<Location, String> {
    Location findById(ObjectId _id);

    Location[] findByLatBetweenAndLngBetween(Double minLat, Double maxLat, Double minLon, Double maxLon);
}
