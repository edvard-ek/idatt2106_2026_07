package edu.ntnu.idi.idatt.core.journal.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ntnu.idi.idatt.core.auth.dto.AuthenticatedUser;
import edu.ntnu.idi.idatt.core.exception.UnauthorizedException;
import edu.ntnu.idi.idatt.core.journal.dto.JournalDTO;
import edu.ntnu.idi.idatt.core.journal.service.JournalService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/journal")
@RequiredArgsConstructor
public class JournalController {

    private final JournalService journalService;

    @GetMapping("/me")
    public ResponseEntity<JournalFeedResponse> getMyJournalEntries(
            @AuthenticationPrincipal AuthenticatedUser user) {
        Long userId = getAuthenticatedUserId(user);
        List<JournalDTO> journalEntries = journalService.findByUserId(userId);
        return ResponseEntity.ok(new JournalFeedResponse(userId, journalEntries));
    }

    @PostMapping("/me/entries")
    public ResponseEntity<JournalDTO> createMyJournalEntry(
            @AuthenticationPrincipal AuthenticatedUser user,
            @Valid @RequestBody UserJournalEntryRequest request) {
        Long userId = getAuthenticatedUserId(user);
        JournalDTO createdEntry = journalService.createUserEntry(userId, request.title(), request.content());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEntry);
    }

    @PutMapping("/me/entries/{entryId}")
    public ResponseEntity<JournalDTO> updateMyJournalEntry(
            @AuthenticationPrincipal AuthenticatedUser user,
            @PathVariable Long entryId,
            @Valid @RequestBody UserJournalEntryRequest request) {
        Long userId = getAuthenticatedUserId(user);
        assertUserCanModifyEntry(userId, entryId);
        JournalDTO updatedEntry = journalService.updateUserEntry(entryId, request.title(), request.content());
        return ResponseEntity.ok(updatedEntry);
    }

    @DeleteMapping("/me/entries/{entryId}")
    public ResponseEntity<Void> deleteMyJournalEntry(
            @AuthenticationPrincipal AuthenticatedUser user,
            @PathVariable Long entryId) {
        Long userId = getAuthenticatedUserId(user);
        assertUserCanModifyEntry(userId, entryId);
        journalService.deleteById(entryId);
        return ResponseEntity.noContent().build();
    }

    private Long getAuthenticatedUserId(AuthenticatedUser user) {
        if (user == null || user.id() == null) {
            throw new UnauthorizedException("Authentication required");
        }
        return user.id();
    }

    private void assertUserCanModifyEntry(Long userId, Long entryId) {
        JournalDTO entry = journalService.findById(entryId);

        if (!Objects.equals(entry.getUserId(), userId)) {
            throw new UnauthorizedException("You can only modify your own journal entries");
        }

        if (!Boolean.TRUE.equals(entry.getCanEdit())) {
            throw new UnauthorizedException("Only user-created journal entries can be modified");
        }
    }

    public record JournalFeedResponse(Long userId, List<JournalDTO> entries) {
    }

    public record UserJournalEntryRequest(
            @NotBlank(message = "Title is required") @Size(max = 120, message = "Title cannot exceed 120 characters") String title,
            @NotBlank(message = "Content is required") @Size(max = 2000, message = "Content cannot exceed 2000 characters") String content) {
    }
}
