package edu.ntnu.idi.idatt.core.journal.mapper;

import org.springframework.stereotype.Component;

import edu.ntnu.idi.idatt.core.journal.dto.JournalDTO;
import edu.ntnu.idi.idatt.core.journal.entity.Journal;

@Component
public class JournalMapper {

    public JournalDTO toDTO(Journal entity) {
        JournalDTO dto = new JournalDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setType(entity.getType().toString());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setMessage(entity.getMessage());
        dto.setSource(entity.getSource() != null ? entity.getSource().toString() : null);
        dto.setSourceId(entity.getSourceId());
        boolean isUserEntry = entity.getType() == Journal.JournalEntryType.USER;
        dto.setCanEdit(isUserEntry);
        dto.setCanDelete(isUserEntry);
        return dto;
    }
    
}
