package edu.ntnu.idi.idatt.core;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  @NotBlank
  private String username;
  protected String email;
  protected String firstName;
  protected String lastName;
  protected String Password; // Encoded
}
