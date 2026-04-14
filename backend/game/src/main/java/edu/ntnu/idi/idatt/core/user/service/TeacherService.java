package edu.ntnu.idi.idatt.core.user.service;

import java.util.List;
import java.util.stream.Collectors;

import edu.ntnu.idi.idatt.core.user.dto.TeacherDTO;
import edu.ntnu.idi.idatt.core.user.entity.Teacher;
import edu.ntnu.idi.idatt.core.user.mapper.TeacherMapper;
import edu.ntnu.idi.idatt.core.user.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {

  private final TeacherRepository teacherRepository;
  private final TeacherMapper teacherMapper;

  public TeacherDTO findById(Long id) {
    Teacher teacher = teacherRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + id));
    return teacherMapper.toDTO(teacher);
  }

  public List<TeacherDTO> findBySchoolId(Long schoolId) {
    return teacherRepository.findBySchoolId(schoolId).stream()
        .map(teacherMapper::toDTO)
        .collect(Collectors.toList());
  }

  public void deleteById(Long id) {
    if (!teacherRepository.existsById(id)) {
      throw new EntityNotFoundException("Teacher not found with id: " + id);
    }
    teacherRepository.deleteById(id);
  }
}
