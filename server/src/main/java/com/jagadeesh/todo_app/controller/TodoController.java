package com.jagadeesh.todo_app.controller;

import com.jagadeesh.todo_app.dto.TodoDTO;
import com.jagadeesh.todo_app.entity.Todo;
import com.jagadeesh.todo_app.entity.User;
import com.jagadeesh.todo_app.payload.response.MessageResponse;
import com.jagadeesh.todo_app.service.AuthService;
import com.jagadeesh.todo_app.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<MessageResponse<List<TodoDTO>>> getAllTodos() {
        User user = authService.getAuthenticatedUser();
        return ResponseEntity.ok(todoService.findByUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse<Optional<TodoDTO>>> getTodoById(@PathVariable Long id) {
        User user = authService.getAuthenticatedUser();
        return ResponseEntity.ok(todoService.getTodoById(id, user));
    }

    @PostMapping
    public ResponseEntity<MessageResponse<TodoDTO>> createTodo(@RequestBody Todo todo, @RequestParam Long categoryId) {
        try {
            User user = authService.getAuthenticatedUser();
            return ResponseEntity.ok(todoService.createTodo(todo, categoryId, user));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse<TodoDTO>> updateTodo(@PathVariable Long id, @RequestBody Todo todoDetails) {
        try {
            User user = authService.getAuthenticatedUser();
            return ResponseEntity.ok(todoService.updateTodo(id, todoDetails, user));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse<String>> deleteTodoById(@PathVariable Long id) {
        User user = authService.getAuthenticatedUser();
        return ResponseEntity.ok(todoService.deleteTodoById(id, user));
    }
}
