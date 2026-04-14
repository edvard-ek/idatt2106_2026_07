package edu.ntnu.idi.idatt.core.avatar.entity;

import edu.ntnu.idi.idatt.core.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Stores a user's selected avatar configuration.
 */
@Entity
@Table(name = "avatars")
@Data
public class Avatar {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(optional = false)
  @JoinColumn(name = "user_id", nullable = false, unique = true)
  private User user;

  @ManyToOne(optional = false)
  @JoinColumn(name = "gender_item_id", nullable = false)
  private AvatarItem gender;

  @ManyToOne(optional = false)
  @JoinColumn(name = "eye_color_item_id", nullable = false)
  private AvatarItem eyeColor;

  @ManyToOne(optional = false)
  @JoinColumn(name = "skin_color_item_id", nullable = false)
  private AvatarItem skinColor;

  @ManyToOne(optional = false)
  @JoinColumn(name = "hair_color_item_id", nullable = false)
  private AvatarItem hairColor;

  @ManyToOne(optional = false)
  @JoinColumn(name = "hair_style_item_id", nullable = false)
  private AvatarItem hairStyle;

  @ManyToOne(optional = false)
  @JoinColumn(name = "upper_body_clothing_item_id", nullable = false)
  private AvatarItem upperBodyClothing;

  @ManyToOne(optional = false)
  @JoinColumn(name = "lower_body_clothing_item_id", nullable = false)
  private AvatarItem lowerBodyClothing;

  @ManyToOne(optional = false)
  @JoinColumn(name = "hat_item_id", nullable = false)
  private AvatarItem hat;
}
