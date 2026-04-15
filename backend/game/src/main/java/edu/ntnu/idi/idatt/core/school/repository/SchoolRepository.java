package edu.ntnu.idi.idatt.core.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ntnu.idi.idatt.core.school.entity.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

  boolean existsByName(String name);
}
