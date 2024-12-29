package com.jagadeesh.todo_app.controller;

import com.jagadeesh.todo_app.dto.CategoryDTO;
import com.jagadeesh.todo_app.entity.Category;
import com.jagadeesh.todo_app.entity.User;
import com.jagadeesh.todo_app.payload.response.MessageResponse;
import com.jagadeesh.todo_app.service.AuthService;
import com.jagadeesh.todo_app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<MessageResponse<List<CategoryDTO>>> getAllCategories() {
        User user = authService.getAuthenticatedUser();
        return ResponseEntity.ok(categoryService.findByUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse<Optional<CategoryDTO>>> getCategoryById(@PathVariable Long id) {
        User user = authService.getAuthenticatedUser();
        return ResponseEntity.ok(categoryService.getCategoryById(id, user));
    }

    @PostMapping
    public ResponseEntity<MessageResponse<CategoryDTO>> createCategory(@RequestBody Category category) {
        User user = authService.getAuthenticatedUser();
        return ResponseEntity.ok(categoryService.createCategory(category, user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse<CategoryDTO>> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        try {
            User user = authService.getAuthenticatedUser();
            return ResponseEntity.ok(categoryService.updateCategory(id, categoryDetails, user));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse<String>> deleteCategoryById(@PathVariable Long id) {
        User user = authService.getAuthenticatedUser();
        return ResponseEntity.ok(categoryService.deleteCategoryById(id, user));
    }
}
