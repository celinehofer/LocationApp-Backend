package com.locationapp.location.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LocationJpaRepository extends JpaRepository<Location, Long>{
        List<Location> findByusername(String username);
}
