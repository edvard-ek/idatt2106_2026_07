package edu.ntnu.idi.idatt.core.classroom.service;

import java.util.List;
import java.util.stream.Collectors;

import edu.ntnu.idi.idatt.core.classroom.dto.ClassroomDTO;
import edu.ntnu.idi.idatt.core.classroom.entity.Classroom;
import edu.ntnu.idi.idatt.core.classroom.mapper.ClassroomMapper;
import edu.ntnu.idi.idatt.core.classroom.repository.ClassroomRepository;
import edu.ntnu.idi.idatt.core.user.entity.Pupil;
import edu.ntnu.idi.idatt.core.user.repository.PupilRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassroomService {

  private final ClassroomRepository classroomRepository;
  private final ClassroomMapper classroomMapper;
  private final PupilRepository pupilRepository;

  public ClassroomDTO findById(Long id) {
    Classroom classroom = classroomRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Classroom not found with id: " + id));
    return classroomMapper.toDTO(classroom);
  }

  public List<ClassroomDTO> findBySchoolId(Long schoolId) {
    return classroomRepository.findBySchoolId(schoolId).stream()
        .map(classroomMapper::toDTO)
        .collect(Collectors.toList());
  }

  public ClassroomDTO findCurrentPupilClassroom() {
    return findByPupilId(getAuthenticatedUserId());
  }

  private ClassroomDTO findByPupilId(Long pupilId) {
    Pupil pupil = pupilRepository.findById(pupilId)
        .orElseThrow(() -> new EntityNotFoundException("Pupil not found with id: " + pupilId));

    Classroom classroom = pupil.getClassroom();
    if (classroom == null) {
      throw new EntityNotFoundException("Classroom not found for pupil id: " + pupilId);
    }

    return classroomMapper.toDTO(classroom);
  }

  public ClassroomDTO save(Classroom classroom) {
    return classroomMapper.toDTO(classroomRepository.save(classroom));
  }

  public void deleteById(Long id) {
    if (!classroomRepository.existsById(id)) {
      throw new EntityNotFoundException("Classroom not found with id: " + id);
    }
    classroomRepository.deleteById(id);
  }

  private Long getAuthenticatedUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null || authentication.getName() == null) {
      throw new IllegalStateException("Authenticated user not found.");
    }

    try {
      return Long.parseLong(authentication.getName());
    } catch (NumberFormatException exception) {
      throw new IllegalStateException("Authenticated user id is invalid.", exception);
    }
  }
}
