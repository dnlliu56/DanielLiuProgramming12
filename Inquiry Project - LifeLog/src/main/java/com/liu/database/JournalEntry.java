package com.liu.database;

import javafx.beans.property.*;
import java.time.LocalDateTime;

public class JournalEntry {
    // These are special variables that can be used with JavaFX UI elements
    private final IntegerProperty id;
    private final StringProperty title;
    private final StringProperty content;
    private final ObjectProperty<LocalDateTime> lastModified;

    // Constructor to set up a new journal entry with all its info
    public JournalEntry(int id, String title, String content, LocalDateTime lastModified) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.content = new SimpleStringProperty(content);
        this.lastModified = new SimpleObjectProperty<>(lastModified);
    }

    // Getters (used to read the values)
    public int getId() { return id.get(); }
    public String getTitle() { return title.get(); }
    public String getContent() { return content.get(); }
    public LocalDateTime getLastModified() { return lastModified.get(); }

    // Setters (used to change the values)
    public void setTitle(String title) { this.title.set(title); }
    public void setContent(String content) { this.content.set(content); }
    public void setLastModified(LocalDateTime time) { this.lastModified.set(time); }

    // These methods are needed for JavaFX UI to automatically update when values change
    public IntegerProperty idProperty() { return id; }
    public StringProperty titleProperty() { return title; }
    public StringProperty contentProperty() { return content; }
    public ObjectProperty<LocalDateTime> lastModifiedProperty() { return lastModified; }
}
