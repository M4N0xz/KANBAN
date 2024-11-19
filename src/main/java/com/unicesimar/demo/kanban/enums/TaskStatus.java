// TaskStatus.java
package com.unicesimar.demo.kanban.enums;

public enum TaskStatus {
    TODO("A Fazer"),
    IN_PROGRESS("Em Progresso"),
    DONE("Concluído");

    private final String description;

    TaskStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}