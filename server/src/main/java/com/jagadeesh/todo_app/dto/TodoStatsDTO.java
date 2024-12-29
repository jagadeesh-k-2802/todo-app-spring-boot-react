package com.jagadeesh.todo_app.dto;

public class TodoStatsDTO {
    private long totalTodos;
    private long pendingTodos;
    private long completedTodos;

    public TodoStatsDTO(long totalTodos, long pendingTodos, long completedTodos) {
        this.totalTodos = totalTodos;
        this.pendingTodos = pendingTodos;
        this.completedTodos = completedTodos;
    }

    // Getters and Setters
    public long getTotalTodos() {
        return totalTodos;
    }

    public void setTotalTodos(long totalTodos) {
        this.totalTodos = totalTodos;
    }

    public long getPendingTodos() {
        return pendingTodos;
    }

    public void setPendingTodos(long pendingTodos) {
        this.pendingTodos = pendingTodos;
    }

    public long getCompletedTodos() {
        return completedTodos;
    }

    public void setCompletedTodos(long completedTodos) {
        this.completedTodos = completedTodos;
    }
}
