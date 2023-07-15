package com.astrotalk.webapp.repositories;

import com.astrotalk.webapp.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, String> {
    Optional<Staff> findByUsername(String username);
}
