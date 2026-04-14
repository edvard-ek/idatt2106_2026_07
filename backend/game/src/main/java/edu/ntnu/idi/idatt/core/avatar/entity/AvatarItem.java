package edu.ntnu.idi.idatt.core.avatar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Represents a selectable avatar item.
 */
@Entity
@Table(name = "avatar_items")
@Data
public class AvatarItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Avatar item name is required")
  @Column(nullable = false, unique = true)
  private String name;

  @NotNull(message = "Avatar slot is required")
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private AvatarSlot slot;
}
