package edu.ntnu.idi.idatt.core.avatar.repository;

import edu.ntnu.idi.idatt.core.avatar.entity.Avatar;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Persists avatar configurations.
 */
@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {

  Optional<Avatar> findByUserId(Long userId);
}
