package com.unicesimar.demo.kanban.enums;

public enum Priority {
    LOW("baixa"),
    MEDIUM("média"),
    HIGH("alta");

    private final String description;

    Priority(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}