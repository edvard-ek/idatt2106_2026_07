package edu.ntnu.idi.idatt.core.avatar.mapper;

import edu.ntnu.idi.idatt.core.avatar.dto.AvatarDTO;
import edu.ntnu.idi.idatt.core.avatar.dto.AvatarItemDTO;
import edu.ntnu.idi.idatt.core.avatar.entity.Avatar;
import edu.ntnu.idi.idatt.core.avatar.entity.AvatarItem;
import org.springframework.stereotype.Component;

/**
 * Maps avatar entities to DTOs.
 */
@Component
public class AvatarMapper {

  /**
   * Maps an avatar entity to a DTO.
   *
   * @param avatar the avatar entity
   * @return the avatar DTO
   */
  public AvatarDTO toDTO(Avatar avatar) {
    return new AvatarDTO(
        avatar.getId(),
        avatar.getUser().getId(),
        avatar.getGender().getId(),
        avatar.getEyeColor().getId(),
        avatar.getSkinColor().getId(),
        avatar.getHairColor().getId(),
        avatar.getHairStyle().getId(),
        avatar.getUpperBodyClothing().getId(),
        avatar.getLowerBodyClothing().getId(),
        avatar.getHat().getId()
    );
  }

  /**
   * Maps an avatar item entity to a DTO.
   *
   * @param item the avatar item entity
   * @return the avatar item DTO
   */
  public AvatarItemDTO toItemDTO(AvatarItem item) {
    return new AvatarItemDTO(
        item.getId(),
        item.getName(),
        item.getSlot()
    );
  }
}
