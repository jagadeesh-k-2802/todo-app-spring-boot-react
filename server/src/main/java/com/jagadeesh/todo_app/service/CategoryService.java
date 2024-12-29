package com.jagadeesh.todo_app.service;

import com.jagadeesh.todo_app.dto.CategoryDTO;
import com.jagadeesh.todo_app.entity.Category;
import com.jagadeesh.todo_app.entity.Todo;
import com.jagadeesh.todo_app.entity.User;
import com.jagadeesh.todo_app.payload.response.MessageResponse;
import com.jagadeesh.todo_app.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public MessageResponse<List<CategoryDTO>> findByUser(User user) {
        List<CategoryDTO> categories = categoryRepository.findByUser(user).stream().map(this::convertToDTO).toList();
        return new MessageResponse<>(true, categories);
    }

    public MessageResponse<Optional<CategoryDTO>> getCategoryById(Long id, User user) {
        Optional<CategoryDTO> category = categoryRepository.findByIdAndUser(id, user).map(this::convertToDTO);

        if (category.isPresent()) {
            return new MessageResponse<>(true, category);
        } else {
            return new MessageResponse<>(false, null);
        }
    }

    public MessageResponse<CategoryDTO> createCategory(Category category, User user) {
        category.setUser(user);
        Category savedCategory = categoryRepository.save(category);
        return new MessageResponse<>(true, convertToDTO(savedCategory));
    }

    public MessageResponse<CategoryDTO> updateCategory(Long id, Category categoryDetails, User user) {
        Category updatedCategory = categoryRepository.findByIdAndUser(id, user).map(category -> {
            category.setName(categoryDetails.getName());
            return categoryRepository.save(category);
        }).orElseThrow(() -> new RuntimeException("Category not found with id " + id));
        return new MessageResponse<>(true, convertToDTO(updatedCategory));
    }

    public MessageResponse<String> deleteCategoryById(Long id, User user) {
        Category category = categoryRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Category not found or you are not authorized to delete it"));
        categoryRepository.delete(category);
        return new MessageResponse<>(true, "Category deleted successfully");
    }

    private CategoryDTO convertToDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName());
    }
}
