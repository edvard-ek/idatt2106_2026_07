package edu.ntnu.idi.idatt.core.journal.entity;

import java.time.LocalDateTime;

import edu.ntnu.idi.idatt.core.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "journals")
@Data
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private JournalEntryType type;

    @Column(nullable = false, length = 120)
    private String title;

    @Column(length = 2000)
    private String content;

    @Column(length = 2000)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(length = 40)
    private JournalSource source;

    @Column(length = 120)
    private String sourceId;

    @Column
    private Integer unlockXp;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum JournalEntryType {
        AUTO,
        USER
    }

    public enum JournalSource {
        CHAPTER_COMPLETED,
        QUEST_COMPLETED,
        LEVEL_UP,
        ITEM_UNLOCKED,
        ACHIEVEMENT_UNLOCKED
    }
}
