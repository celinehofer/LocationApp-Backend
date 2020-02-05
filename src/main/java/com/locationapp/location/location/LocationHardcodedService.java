package com.locationapp.location.location;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class LocationHardcodedService {

    private static List<Location> locations = new ArrayList<>();
    private static long idCounter = 0;

    static {
        locations.add(new Location(++idCounter, "in28minutes", "Learn to Dance", new Date()));
        locations.add(new Location(++idCounter, "in28minutes", "Learn about", new Date()));
        locations.add(new Location(++idCounter, "in28minutes", "Learn about Angular", new Date()));
    }

    public List<Location> findAll() {
        return locations;
    }

    public Location save(Location location) {
        if (location.getId() == -1) {
            location.setId(++idCounter);
            locations.add(location);
        } else {
            deleteById(location.getId());
            locations.add(location);
        }
        return location;
    }

    public Location deleteById(long id) {
        Location location = findById(id);
        if (location == null)
            return null;

        if (locations.remove(location)) {
            return location;
        }
        return null;
    }

    public Location findById(long id) {
        for (Location location : locations) {
            if (location.getId() == id) {
                return location;
            }
        }
        return null;
    }
}

