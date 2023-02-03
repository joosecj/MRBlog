package com.mr.blog.services;

import com.mr.blog.dto.v1.CategoryDTO;
import com.mr.blog.entities.Category;
import com.mr.blog.repositories.CategoryRepository;
import com.mr.blog.services.exeptions.DataBaseException;
import com.mr.blog.services.exeptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CategoryServices {
    @Autowired
    private CategoryRepository categoryRepository;

    private static final String CATEGORY_NOT_FOUND = "Category not found";

    @Transactional(readOnly = true)
    public CategoryDTO findById(long id) {
        Category categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> categoryNotFound(CATEGORY_NOT_FOUND));
        return new CategoryDTO(categoryEntity);
    }


    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAll(Pageable pageable) {
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryPage.map(CategoryDTO::new);
    }

    public CategoryDTO insert(CategoryDTO categoryDTO) {
        try {
            Category categoryEntity = new Category();
            copyDtoToEntity(categoryDTO, categoryEntity);
            categoryRepository.save(categoryEntity);
            return new CategoryDTO(categoryEntity);
        } catch (ConstraintViolationException e) {
            throw categoryNotFound("Registered category");
        }
    }

    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        try {
            Category categoryEntity = categoryRepository.getReferenceById(id);
            copyDtoToEntity(categoryDTO, categoryEntity);
            return new CategoryDTO(categoryRepository.save(categoryEntity));
        } catch (EntityNotFoundException e) {
            throw categoryNotFound(CATEGORY_NOT_FOUND);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw categoryNotFound(CATEGORY_NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Referential integrity failure");
        }
    }

    private void copyDtoToEntity(CategoryDTO categoryDTO, Category categoryEntity) {
        categoryEntity.setName(categoryDTO.getName());
        categoryEntity.setDescription(categoryDTO.getDescription());
    }

    private static ResourceNotFoundException categoryNotFound(String message) {
        return new ResourceNotFoundException(message);
    }
}
