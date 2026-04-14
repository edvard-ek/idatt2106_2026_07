package edu.ntnu.idi.idatt.core.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  @NotBlank(message = "Username is required")
  @Column(nullable = false, unique = true)
  protected String username;

  @NotBlank(message = "Email is required")
  @Email(message = "Email must be valid")
  @Column(nullable = false, unique = true)
  protected String email;

  @NotBlank(message = "First name is required")
  protected String firstName;

  @NotBlank(message = "Last name is required")
  protected String lastName;

  @NotBlank(message = "Password is required")
  @Column(nullable = false)
  protected String password; // Encoded
}
