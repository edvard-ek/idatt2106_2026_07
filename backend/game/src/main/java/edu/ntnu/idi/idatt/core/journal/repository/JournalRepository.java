package edu.ntnu.idi.idatt.core.journal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ntnu.idi.idatt.core.journal.entity.Journal;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Long> {

    List<Journal> findByUserId(Long userId);

    List<Journal> findByUserIdOrderByCreatedAtAsc(Long userId);
    
}
