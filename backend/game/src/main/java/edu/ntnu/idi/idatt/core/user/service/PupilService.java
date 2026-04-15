package edu.ntnu.idi.idatt.core.user.service;

import java.util.List;

import edu.ntnu.idi.idatt.core.user.dto.PupilDTO;
import edu.ntnu.idi.idatt.core.user.entity.Pupil;
import edu.ntnu.idi.idatt.core.user.mapper.PupilMapper;
import edu.ntnu.idi.idatt.core.exception.ResourceNotFoundException;
import edu.ntnu.idi.idatt.core.user.repository.PupilRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PupilService {

  private final PupilRepository pupilRepository;
  private final PupilMapper pupilMapper;

  public PupilDTO findById(Long id) {
    Pupil pupil = pupilRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Pupil not found by id", id));
    return pupilMapper.toDTO(pupil);
  }

  public List<PupilDTO> findByClassroomId(Long classroomId) {
    return pupilRepository.findByClassroomId(classroomId).stream()
        .map(pupilMapper::toDTO)
        .toList();
  }

  public void deleteById(Long id) {
    if (!pupilRepository.existsById(id)) {
      throw new ResourceNotFoundException("Pupil not found by id", id);
    }
    pupilRepository.deleteById(id);
  }

  @Transactional
  public void updateXpById(Long id, int gainedXp) {
    Pupil pupil = pupilRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Pupil not found by id", id));
    pupil.setXp(pupil.getXp() + gainedXp);
  }
}
