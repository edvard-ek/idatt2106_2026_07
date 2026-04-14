package edu.ntnu.idi.idatt.core.user.repository;

import java.util.List;

import edu.ntnu.idi.idatt.core.user.entity.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PupilRepository extends JpaRepository<Pupil, Long> {

  List<Pupil> findByClassroomId(Long classroomId);
}
