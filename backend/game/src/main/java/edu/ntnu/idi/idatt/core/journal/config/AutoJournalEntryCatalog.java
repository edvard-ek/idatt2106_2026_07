package edu.ntnu.idi.idatt.core.journal.config;

import java.util.EnumMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import edu.ntnu.idi.idatt.core.journal.entity.Journal;

@Component
public class AutoJournalEntryCatalog {

    private final Map<Journal.JournalSource, AutoJournalTemplate> templates;

    public AutoJournalEntryCatalog() {
        EnumMap<Journal.JournalSource, AutoJournalTemplate> catalog = new EnumMap<>(Journal.JournalSource.class);

        // Centralized place for all automatic journal templates and unlock thresholds.
        catalog.put(
                Journal.JournalSource.CHAPTER_COMPLETED,
                new AutoJournalTemplate(300, "Kapittel fullfort", "Du fullforte et kapittel og rykket videre i historien."));
        catalog.put(
                Journal.JournalSource.QUEST_COMPLETED,
                new AutoJournalTemplate(100, "Oppdrag fullfort", "Du fullforte et oppdrag og fikk nye erfaringer."));
        catalog.put(
                Journal.JournalSource.LEVEL_UP,
                new AutoJournalTemplate(600, "Niva oppnadd", "Du naadde et nytt niva i spillet."));
        catalog.put(
                Journal.JournalSource.ITEM_UNLOCKED,
                new AutoJournalTemplate(450, "Item last opp", "Du last opp et nytt kosmetisk item."));
        catalog.put(
                Journal.JournalSource.ACHIEVEMENT_UNLOCKED,
                new AutoJournalTemplate(900, "Prestasjon last opp", "Du fikk en ny prestasjon."));

        this.templates = Map.copyOf(catalog);
    }

    public AutoJournalTemplate getBySource(Journal.JournalSource source) {
        AutoJournalTemplate template = templates.get(source);
        if (template == null) {
            throw new IllegalArgumentException("No auto journal template configured for source: " + source);
        }
        return template;
    }

    public record AutoJournalTemplate(int unlockXp, String title, String message) {
    }
}
