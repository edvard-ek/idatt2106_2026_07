package edu.ntnu.idi.idatt.core.avatar.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.core.avatar.dto.AvatarDTO;
import edu.ntnu.idi.idatt.core.avatar.dto.AvatarItemDTO;
import edu.ntnu.idi.idatt.core.avatar.dto.UpdateAvatarRequest;
import edu.ntnu.idi.idatt.core.avatar.entity.AvatarSlot;
import edu.ntnu.idi.idatt.core.avatar.repository.AvatarRepository;
import edu.ntnu.idi.idatt.core.classroom.entity.Classroom;
import edu.ntnu.idi.idatt.core.classroom.repository.ClassroomRepository;
import edu.ntnu.idi.idatt.core.user.entity.Pupil;
import edu.ntnu.idi.idatt.core.user.repository.PupilRepository;
import edu.ntnu.idi.idatt.game.GameApplication;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * Tests {@link AvatarService}.
 */
@SpringBootTest(classes = GameApplication.class)
@Transactional
class AvatarServiceIntegrationTest {

  @Autowired
  private AvatarService avatarService;

  @Autowired
  private AvatarRepository avatarRepository;

  @Autowired
  private PupilRepository pupilRepository;

  @Autowired
  private ClassroomRepository classroomRepository;

  /**
   * Tests that an avatar is created for a user.
   */
  @Test
  void saveOrUpdateCreatesAvatarForUser() {
    Long userId = createTestPupil().getId();
    long initialCount = avatarRepository.count();
    AvatarDTO avatar = avatarService.saveOrUpdate(userId, validRequest());

    assertNotNull(avatar.id());
    assertEquals(userId, avatar.userId());
    assertEquals(1L, avatar.genderItemId());
    assertEquals(initialCount + 1, avatarRepository.count());
  }

  /**
   * Tests that an existing avatar is updated.
   */
  @Test
  void saveOrUpdateUpdatesExistingAvatar() {
    Long userId = createTestPupil().getId();
    AvatarDTO created = avatarService.saveOrUpdate(userId, validRequest());
    long countAfterCreate = avatarRepository.count();
    UpdateAvatarRequest updatedRequest = new UpdateAvatarRequest(
        2L, 4L, 6L, 8L, 10L, 12L, 14L, 16L
    );

    AvatarDTO updated = avatarService.saveOrUpdate(userId, updatedRequest);

    assertEquals(created.id(), updated.id());
    assertEquals(2L, updated.genderItemId());
    assertEquals(16L, updated.hatItemId());
    assertEquals(countAfterCreate, avatarRepository.count());
  }

  /**
   * Tests that a user's avatar can be fetched.
   */
  @Test
  void findByUserIdReturnsExistingAvatar() {
    Long userId = createTestPupil().getId();
    AvatarDTO created = avatarService.saveOrUpdate(userId, validRequest());

    AvatarDTO found = avatarService.findByUserId(userId);

    assertEquals(created.id(), found.id());
    assertEquals(created.userId(), found.userId());
    assertEquals(created.hairStyleItemId(), found.hairStyleItemId());
  }

  /**
   * Tests that fetching a missing avatar fails.
   */
  @Test
  void findByUserIdThrowsWhenAvatarDoesNotExist() {
    Long userId = createTestPupil().getId();

    assertThrows(EntityNotFoundException.class, () -> avatarService.findByUserId(userId));
  }

  /**
   * Tests that an unknown avatar item fails validation.
   */
  @Test
  void saveOrUpdateThrowsWhenAvatarItemDoesNotExist() {
    Long userId = createTestPupil().getId();
    UpdateAvatarRequest request = new UpdateAvatarRequest(
        999L, 3L, 5L, 7L, 9L, 11L, 13L, 15L
    );

    assertThrows(EntityNotFoundException.class, () -> avatarService.saveOrUpdate(userId, request));
  }

  /**
   * Tests that an avatar item from the wrong slot fails validation.
   */
  @Test
  void saveOrUpdateThrowsWhenAvatarItemBelongsToWrongSlot() {
    Long userId = createTestPupil().getId();
    UpdateAvatarRequest request = new UpdateAvatarRequest(
        3L, 4L, 5L, 7L, 9L, 11L, 13L, 15L
    );

    assertThrows(IllegalArgumentException.class, () -> avatarService.saveOrUpdate(userId, request));
  }

  /**
   * Tests that avatar items can be filtered by slot.
   */
  @Test
  void findItemsBySlotReturnsOnlyMatchingItems() {
    List<AvatarItemDTO> items = avatarService.findItemsBySlot(AvatarSlot.HAT);

    assertEquals(2, items.size());
    assertEquals(AvatarSlot.HAT, items.getFirst().slot());
    assertEquals(AvatarSlot.HAT, items.getLast().slot());
  }

  private UpdateAvatarRequest validRequest() {
    return new UpdateAvatarRequest(
        1L, 3L, 5L, 7L, 9L, 11L, 13L, 15L
    );
  }

  private Pupil createTestPupil() {
    Classroom classroom = classroomRepository.findById(1L)
        .orElseThrow(() -> new EntityNotFoundException("Classroom not found with id: 1"));

    Pupil pupil = new Pupil();
    String unique = String.valueOf(System.nanoTime());
    pupil.setUsername("avatar-test-" + unique);
    pupil.setEmail("avatar-test-" + unique + "@osloskolen.no");
    pupil.setFirstName("Avatar");
    pupil.setLastName("Test");
    pupil.setPassword("encoded-password");
    pupil.setClassroom(classroom);

    return pupilRepository.save(pupil);
  }
}
