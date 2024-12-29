package com.jagadeesh.todo_app.dto;

public class TodoDTO {
    private Long id;
    private String title;
    private boolean completed;
    private CategoryDTO category;

    public TodoDTO(Long id, String title, boolean completed, CategoryDTO category) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
}
