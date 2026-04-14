package edu.ntnu.idi.idatt.core.school.service;

import java.util.List;
import java.util.stream.Collectors;

import edu.ntnu.idi.idatt.core.school.dto.SchoolDTO;
import edu.ntnu.idi.idatt.core.school.entity.School;
import edu.ntnu.idi.idatt.core.school.mapper.SchoolMapper;
import edu.ntnu.idi.idatt.core.school.repository.SchoolRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolService {

  private final SchoolRepository schoolRepository;
  private final SchoolMapper schoolMapper;

  public List<SchoolDTO> findAll() {
    return schoolRepository.findAll().stream()
        .map(schoolMapper::toDTO)
        .collect(Collectors.toList());
  }

  public SchoolDTO findById(Long id) {
    School school = schoolRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("School not found with id: " + id));
    return schoolMapper.toDTO(school);
  }

  public SchoolDTO save(School school) {
    return schoolMapper.toDTO(schoolRepository.save(school));
  }

  public void deleteById(Long id) {
    if (!schoolRepository.existsById(id)) {
      throw new EntityNotFoundException("School not found with id: " + id);
    }
    schoolRepository.deleteById(id);
  }
}
