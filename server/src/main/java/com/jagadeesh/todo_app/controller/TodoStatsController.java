package com.jagadeesh.todo_app.controller;

import com.jagadeesh.todo_app.dto.TodoStatsDTO;
import com.jagadeesh.todo_app.entity.User;
import com.jagadeesh.todo_app.payload.response.MessageResponse;
import com.jagadeesh.todo_app.service.AuthService;
import com.jagadeesh.todo_app.service.TodoStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
public class TodoStatsController {

    @Autowired
    private TodoStatsService todoStatsService;

    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<MessageResponse<TodoStatsDTO>> getStats() {
        User user = authService.getAuthenticatedUser();
        return ResponseEntity.ok(todoStatsService.getTodoStats(user));
    }
}
