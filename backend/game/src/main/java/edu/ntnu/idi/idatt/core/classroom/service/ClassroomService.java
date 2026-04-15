package edu.ntnu.idi.idatt.core.classroom.service;

import java.util.List;
import java.util.stream.Collectors;

import edu.ntnu.idi.idatt.core.classroom.dto.ClassroomDTO;
import edu.ntnu.idi.idatt.core.classroom.entity.Classroom;
import edu.ntnu.idi.idatt.core.classroom.mapper.ClassroomMapper;
import edu.ntnu.idi.idatt.core.classroom.repository.ClassroomRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassroomService {

  private final ClassroomRepository classroomRepository;
  private final ClassroomMapper classroomMapper;

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

  public ClassroomDTO save(Classroom classroom) {
    return classroomMapper.toDTO(classroomRepository.save(classroom));
  }

  public void deleteById(Long id) {
    if (!classroomRepository.existsById(id)) {
      throw new EntityNotFoundException("Classroom not found with id: " + id);
    }
    classroomRepository.deleteById(id);
  }
}
