package com.jagadeesh.todo_app.repository;

import com.jagadeesh.todo_app.entity.Todo;
import com.jagadeesh.todo_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUser(User user);
    Optional<Todo> findByIdAndUser(Long id, User user);

    long countByUser(User user);
    long countByUserAndCompleted(User user, boolean completed);
}
