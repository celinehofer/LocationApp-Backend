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
public class LocationJpaRessource {

    @Autowired
    private LocationHardcodedService locationService;

    @Autowired
    private LocationJpaRepository locationJpaRepository;

    @GetMapping("/jpa/users/{username}/locations")
    public List<Location> getAllLocations(@PathVariable String username) {
        return locationJpaRepository.findByusername(username);
    }

    @GetMapping("/jpa/users/{username}/locations/{id}")
    public Location getLocation(@PathVariable String username, @PathVariable long id) {
        return locationJpaRepository.findById(id).get();
    }

    @DeleteMapping("/jpa/users/{username}/locations/{id}")
    public ResponseEntity<Void> deleteLocation(
            @PathVariable String username, @PathVariable long id) {

        locationJpaRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/jpa/users/{username}/locations/{id}")
    public ResponseEntity<Location> updateLocation(
            @PathVariable String username,
            @PathVariable long id, @RequestBody Location location) {

        location.setUsername(username);

        Location locationUpdated = locationJpaRepository.save(location);

        return new ResponseEntity<Location>(location, HttpStatus.OK);
    }

    @PostMapping("/jpa/users/{username}/locations")
    public ResponseEntity<Void> createLocation(
            @PathVariable String username, @RequestBody Location location) {

        location.setUsername(username);

        Location createdLocation = locationJpaRepository.save(location);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdLocation.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
