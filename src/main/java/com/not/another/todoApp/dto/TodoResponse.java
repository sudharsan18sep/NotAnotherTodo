package com.not.another.todoApp.dto;

import java.time.LocalDate;

public class TodoResponse {



    private String Id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDate deadline;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
