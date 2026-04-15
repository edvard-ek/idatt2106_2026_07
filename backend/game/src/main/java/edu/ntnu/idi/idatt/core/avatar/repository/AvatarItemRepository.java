package edu.ntnu.idi.idatt.core.avatar.repository;

import edu.ntnu.idi.idatt.core.avatar.entity.AvatarItem;
import edu.ntnu.idi.idatt.core.avatar.entity.AvatarSlot;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Persists selectable avatar items.
 */
@Repository
public interface AvatarItemRepository extends JpaRepository<AvatarItem, Long> {

  List<AvatarItem> findBySlot(AvatarSlot slot);
}
