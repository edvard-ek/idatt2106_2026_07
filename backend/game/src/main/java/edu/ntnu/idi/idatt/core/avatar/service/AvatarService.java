package edu.ntnu.idi.idatt.core.avatar.service;

import edu.ntnu.idi.idatt.core.avatar.dto.AvatarDTO;
import edu.ntnu.idi.idatt.core.avatar.dto.AvatarItemDTO;
import edu.ntnu.idi.idatt.core.avatar.dto.UpdateAvatarRequest;
import edu.ntnu.idi.idatt.core.avatar.entity.Avatar;
import edu.ntnu.idi.idatt.core.avatar.entity.AvatarItem;
import edu.ntnu.idi.idatt.core.avatar.entity.AvatarSlot;
import edu.ntnu.idi.idatt.core.avatar.mapper.AvatarMapper;
import edu.ntnu.idi.idatt.core.avatar.repository.AvatarItemRepository;
import edu.ntnu.idi.idatt.core.avatar.repository.AvatarRepository;
import edu.ntnu.idi.idatt.core.user.entity.User;
import edu.ntnu.idi.idatt.core.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Handles avatar operations and validation.
 */
@Service
@RequiredArgsConstructor
public class AvatarService {

  private final AvatarRepository avatarRepository;
  private final AvatarItemRepository avatarItemRepository;
  private final AvatarMapper avatarMapper;
  private final UserRepository userRepository;

  /**
   * Finds the avatar for a user.
   *
   * @param userId the user id
   * @return the avatar configuration
   */
  public AvatarDTO findByUserId(Long userId) {
    Avatar avatar = avatarRepository.findByUserId(userId)
        .orElseThrow(() -> new EntityNotFoundException("Avatar not found for user id: " + userId));
    return avatarMapper.toDTO(avatar);
  }

  /**
   * Finds all available avatar items.
   *
   * @return the available avatar items
   */
  public List<AvatarItemDTO> findAllItems() {
    return avatarItemRepository.findAll().stream()
        .map(avatarMapper::toItemDTO)
        .collect(Collectors.toList());
  }

  /**
   * Finds avatar items for a slot.
   *
   * @param slot the avatar slot
   * @return the avatar items for the slot
   */
  public List<AvatarItemDTO> findItemsBySlot(AvatarSlot slot) {
    return avatarItemRepository.findBySlot(slot).stream()
        .map(avatarMapper::toItemDTO)
        .collect(Collectors.toList());
  }

  /**
   * Saves or updates a user's avatar.
   *
   * @param userId the user id
   * @param request the avatar update request
   * @return the saved avatar configuration
   */
  public AvatarDTO saveOrUpdate(Long userId, UpdateAvatarRequest request) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

    Avatar avatar = avatarRepository.findByUserId(userId)
        .orElseGet(Avatar::new);

    avatar.setUser(user);
    avatar.setGender(getAvatarItem(request.genderId(), AvatarSlot.GENDER));
    avatar.setEyeColor(getAvatarItem(request.eyeColorId(), AvatarSlot.EYE_COLOR));
    avatar.setSkinColor(getAvatarItem(request.skinColorId(), AvatarSlot.SKIN_COLOR));
    avatar.setHairColor(getAvatarItem(request.hairColorId(), AvatarSlot.HAIR_COLOR));
    avatar.setHairStyle(getAvatarItem(request.hairStyleId(), AvatarSlot.HAIR_STYLE));
    avatar.setUpperBodyClothing(
        getAvatarItem(request.upperBodyClothingId(), AvatarSlot.UPPER_BODY_CLOTHING));
    avatar.setLowerBodyClothing(
        getAvatarItem(request.lowerBodyClothingId(), AvatarSlot.LOWER_BODY_CLOTHING));
    avatar.setHat(getAvatarItem(request.hatId(), AvatarSlot.HAT));

    return avatarMapper.toDTO(avatarRepository.save(avatar));
  }

  private AvatarItem getAvatarItem(Long itemId, AvatarSlot expectedSlot) {
    AvatarItem item = avatarItemRepository.findById(itemId)
        .orElseThrow(() -> new EntityNotFoundException("Avatar item not found with id: " + itemId));

    if (item.getSlot() != expectedSlot) {
      throw new IllegalArgumentException(
          "Avatar item with id " + itemId + " does not belong to slot " + expectedSlot);
    }

    return item;
  }
}
