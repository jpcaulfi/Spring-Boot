package com.canopy.canopyspring.rescues;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RescueRepository extends JpaRepository<Rescue, Long> {
}
