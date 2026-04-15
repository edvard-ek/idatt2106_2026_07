package edu.ntnu.idi.idatt.core.classroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ntnu.idi.idatt.core.classroom.entity.Classroom;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

  List<Classroom> findBySchoolId(Long schoolId);

  List<Classroom> findByHeadTeacherId(Long headTeacherId);
}
