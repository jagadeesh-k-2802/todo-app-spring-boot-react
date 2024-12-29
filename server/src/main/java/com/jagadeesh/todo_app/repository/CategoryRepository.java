package com.jagadeesh.todo_app.repository;

import com.jagadeesh.todo_app.entity.Category;
import com.jagadeesh.todo_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUser(User user);
    Optional<Category> findByIdAndUser(Long id, User user);
}
