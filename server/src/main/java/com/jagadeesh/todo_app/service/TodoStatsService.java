package com.jagadeesh.todo_app.service;

import com.jagadeesh.todo_app.dto.TodoStatsDTO;
import com.jagadeesh.todo_app.entity.User;
import com.jagadeesh.todo_app.payload.response.MessageResponse;
import com.jagadeesh.todo_app.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoStatsService {

    @Autowired
    private TodoRepository todoRepository;

    public MessageResponse<TodoStatsDTO> getTodoStats(User user) {
        long totalTodos = todoRepository.countByUser(user);
        long completedTodos = todoRepository.countByUserAndCompleted(user, true);
        long pendingTodos = totalTodos - completedTodos;
        TodoStatsDTO stats = new TodoStatsDTO(totalTodos, pendingTodos, completedTodos);
        return new MessageResponse<>(true, stats);
    }
}
