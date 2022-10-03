package com.mr.blog.services;

import com.mr.blog.dto.CategoryDTO;
import com.mr.blog.dto.UserDTO;
import com.mr.blog.entities.Category;
import com.mr.blog.entities.User;
import com.mr.blog.repositories.CategoryRepository;
import com.mr.blog.services.exeptions.ResourceNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CategoryServices {
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public CategoryDTO findById(long id) {
        Category categoryEntity = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return new CategoryDTO(categoryEntity);
    }

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAll(Pageable pageable) {
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryPage.map(CategoryDTO::new);
    }

    @Transactional(readOnly = false)
    public CategoryDTO insert(CategoryDTO categoryDTO) {
        try {
            Category categoryEntity = new Category();
            copyDtoToEntity(categoryDTO, categoryEntity);
            categoryRepository.save(categoryEntity);
            return new CategoryDTO(categoryEntity);
        } catch (ConstraintViolationException e) {
            throw new ResourceNotFoundException("E-mail já cadastrado");
        }
    }

    private void copyDtoToEntity(CategoryDTO categoryDTO, Category categoryEntity) {
        categoryEntity.setName(categoryDTO.getName());
        categoryEntity.setDescription(categoryDTO.getDescription());
    }
}
