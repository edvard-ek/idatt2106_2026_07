package edu.ntnu.idi.idatt.core.user.entity;

import edu.ntnu.idi.idatt.core.classroom.entity.Classroom;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "pupils")
@Data
@EqualsAndHashCode(callSuper = true)
public class Pupil extends User {

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;
}
