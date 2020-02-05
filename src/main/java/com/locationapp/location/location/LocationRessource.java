package com.locationapp.location.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LocationRessource {

        @Autowired
        private LocationHardcodedService locationService;

        @GetMapping("/users/{username}/locations")
        public List<Location> getAllLocations(@PathVariable String username) {
            return locationService.findAll();
        }

        @GetMapping("/users/{username}/locations/{id}")
        public Location getLocation(@PathVariable String username, @PathVariable long id) {
            return locationService.findById(id);
        }

        @DeleteMapping("/users/{username}/locations/{id}")
        public ResponseEntity<Void> deleteLocation(
                @PathVariable String username, @PathVariable long id) {
            Location location = locationService.deleteById(id);
            if (location != null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        }

        @PutMapping("/users/{username}/locations/{id}")
        public ResponseEntity<Location> updateLocation(
                @PathVariable String username, @PathVariable long id, @RequestBody Location location) {
            Location locationUpdated = locationService.save(location);
            return new ResponseEntity<Location>(location, HttpStatus.OK);
        }

        @PostMapping("/users/{username}/locations")
        public ResponseEntity<?> createLocation(
                @PathVariable String username, @RequestBody Location location) {
            Location createdLocation = locationService.save(location);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdLocation.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }
    }

