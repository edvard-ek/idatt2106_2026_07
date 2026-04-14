package edu.ntnu.idi.idatt.core.user.repository;

import java.util.List;

import edu.ntnu.idi.idatt.core.user.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

  List<Teacher> findBySchoolId(Long schoolId);
}
