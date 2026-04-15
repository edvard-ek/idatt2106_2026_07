package edu.ntnu.idi.idatt.core.journal.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.ntnu.idi.idatt.core.journal.config.AutoJournalEntryCatalog;
import edu.ntnu.idi.idatt.core.journal.dto.JournalDTO;
import edu.ntnu.idi.idatt.core.journal.entity.Journal;
import edu.ntnu.idi.idatt.core.journal.mapper.JournalMapper;
import edu.ntnu.idi.idatt.core.journal.repository.JournalRepository;
import edu.ntnu.idi.idatt.core.user.entity.User;
import edu.ntnu.idi.idatt.core.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
    
@Service
@RequiredArgsConstructor
public class JournalService {

    private final JournalRepository journalRepository;
    private final JournalMapper journalMapper;
    private final UserRepository userRepository;
    private final AutoJournalEntryCatalog autoJournalEntryCatalog;

    public JournalDTO findById(Long id) {
        Journal journal = journalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Journal not found with id: " + id));
        return journalMapper.toDTO(journal);
    }

    public List<JournalDTO> findByUserId(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        Integer currentXp = user.getXp();
        int userXp = currentXp != null ? currentXp : 0;

        return journalRepository.findByUserIdOrderByCreatedAtAsc(userId).stream()
            .filter(journal -> isVisibleForUserXp(journal, userXp))
                .map(journalMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        if (!journalRepository.existsById(id)) {
            throw new EntityNotFoundException("Journal not found with id: " + id);
        }
        journalRepository.deleteById(id);
    }

    public JournalDTO createUserEntry(Long userId, String title, String content) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
    
    Journal journal = new Journal();
    journal.setUser(user);
    journal.setType(Journal.JournalEntryType.USER);
    journal.setTitle(title);
    journal.setContent(content);
    
    Journal saved = journalRepository.save(journal);
    return journalMapper.toDTO(saved);
    }

    public JournalDTO updateUserEntry(Long id, String title, String content) {
        Journal journal = journalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Journal not found with id: " + id));
        
        if (journal.getType() != Journal.JournalEntryType.USER) {
            throw new IllegalArgumentException("Only user entries can be updated");
        }
        
        journal.setTitle(title);
        journal.setContent(content);
        
        Journal updated = journalRepository.save(journal);
        return journalMapper.toDTO(updated);
    }

    public JournalDTO createAutoEntry(Long userId, Journal.JournalSource source, String sourceId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        AutoJournalEntryCatalog.AutoJournalTemplate template = autoJournalEntryCatalog.getBySource(source);

        Journal journal = new Journal();
        journal.setUser(user);
        journal.setType(Journal.JournalEntryType.AUTO);
        journal.setSource(source);
        journal.setSourceId(sourceId);
        journal.setTitle(template.title());
        journal.setMessage(template.message());
        journal.setUnlockXp(template.unlockXp());

        Journal saved = journalRepository.save(journal);
        return journalMapper.toDTO(saved);
    }

    // Backward-compatible helper; prefer the overload with source and sourceId.
    public JournalDTO createAutoEntry(Long userId) {
        return createAutoEntry(userId, Journal.JournalSource.QUEST_COMPLETED, null);
    }

    private boolean isVisibleForUserXp(Journal journal, int userXp) {
        if (journal.getType() == Journal.JournalEntryType.USER) {
            return true;
        }

        Integer unlockXp = journal.getUnlockXp();
        return unlockXp == null || userXp >= unlockXp;
    }
}
