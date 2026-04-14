package edu.ntnu.idi.idatt.core.user.entity;

import edu.ntnu.idi.idatt.core.school.entity.School;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "teachers")
@Data
@EqualsAndHashCode(callSuper = true)
public class Teacher extends User {

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;
}
