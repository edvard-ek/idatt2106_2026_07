package edu.ntnu.idi.idatt.core.journal.dto;

import lombok.Data;

@Data
public class JournalDTO {

    private Long id;
    private Long userId;
    private String type;
    private String title;
    private String content;
    private String message;
    private String source;
    private String sourceId;
    private Boolean canEdit;
    private Boolean canDelete;
}
