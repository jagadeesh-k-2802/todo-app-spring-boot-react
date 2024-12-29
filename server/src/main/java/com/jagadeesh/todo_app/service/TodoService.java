package com.jagadeesh.todo_app.service;

import com.jagadeesh.todo_app.dto.CategoryDTO;
import com.jagadeesh.todo_app.dto.TodoDTO;
import com.jagadeesh.todo_app.entity.Category;
import com.jagadeesh.todo_app.entity.Todo;
import com.jagadeesh.todo_app.entity.User;
import com.jagadeesh.todo_app.payload.response.MessageResponse;
import com.jagadeesh.todo_app.repository.CategoryRepository;
import com.jagadeesh.todo_app.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public MessageResponse<List<TodoDTO>> findByUser(User user) {
        List<TodoDTO> todos = todoRepository.findByUser(user).stream().map(this::convertToDTO).toList();
        return new MessageResponse<>(true, todos);
    }

    public MessageResponse<Optional<TodoDTO>> getTodoById(Long id, User user) {
        Optional<TodoDTO> todo = todoRepository.findByIdAndUser(id, user).map(this::convertToDTO);

        if (todo.isPresent()) {
            return new MessageResponse<>(true, todo);
        } else {
            return new MessageResponse<>(false, null);
        }
    }

    public MessageResponse<TodoDTO> createTodo(Todo todo, Long categoryId, User user) {
        todo.setUser(user);
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + categoryId));
        todo.setCategory(category);
        Todo savedTodo = todoRepository.save(todo);
        return new MessageResponse<>(true, convertToDTO(savedTodo));
    }

    public MessageResponse<TodoDTO> updateTodo(Long id, Todo todoDetails, User user) {
        Todo updatedTodo = todoRepository.findByIdAndUser(id, user).map(todo -> {
            todo.setTitle(todoDetails.getTitle());
            todo.setCompleted(todoDetails.isCompleted());
            return todoRepository.save(todo);
        }).orElseThrow(() -> new RuntimeException("Todo not found with id " + id));
        return new MessageResponse<>(true, convertToDTO(updatedTodo));
    }

    public MessageResponse<String> deleteTodoById(Long id, User user) {
        Todo todo = todoRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Todo not found or you are not authorized to delete it"));
        todoRepository.delete(todo);
        return new MessageResponse<>(true, "Todo deleted successfully");
    }

    private TodoDTO convertToDTO(Todo todo) {
        CategoryDTO categoryDTO = new CategoryDTO(todo.getCategory().getId(), todo.getCategory().getName());
        return new TodoDTO(todo.getId(), todo.getTitle(), todo.isCompleted(), categoryDTO);
    }
}
