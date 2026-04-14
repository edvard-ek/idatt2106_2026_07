package edu.ntnu.idi.idatt.core.user.service;

import java.util.List;
import java.util.stream.Collectors;

import edu.ntnu.idi.idatt.core.user.dto.PupilDTO;
import edu.ntnu.idi.idatt.core.user.entity.Pupil;
import edu.ntnu.idi.idatt.core.user.mapper.PupilMapper;
import edu.ntnu.idi.idatt.core.user.repository.PupilRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PupilService {

  private final PupilRepository pupilRepository;
  private final PupilMapper pupilMapper;

  public PupilDTO findById(Long id) {
    Pupil pupil = pupilRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Pupil not found with id: " + id));
    return pupilMapper.toDTO(pupil);
  }

  public List<PupilDTO> findByClassroomId(Long classroomId) {
    return pupilRepository.findByClassroomId(classroomId).stream()
        .map(pupilMapper::toDTO)
        .collect(Collectors.toList());
  }

  public void deleteById(Long id) {
    if (!pupilRepository.existsById(id)) {
      throw new EntityNotFoundException("Pupil not found with id: " + id);
    }
    pupilRepository.deleteById(id);
  }
}
