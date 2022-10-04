package com.mr.blog.services;

import com.mr.blog.dto.PostCategoryUserDTO;
import com.mr.blog.entities.Category;
import com.mr.blog.entities.Post;
import com.mr.blog.entities.User;
import com.mr.blog.repositories.CategoryRepository;
import com.mr.blog.repositories.PostRepository;
import com.mr.blog.repositories.UserRepository;
import com.mr.blog.services.exeptions.ResourceNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = false)
    public PostCategoryUserDTO insert(PostCategoryUserDTO postCategoryUserDTO) {
        try {
            Post postEntity = new Post();
            copyDtoToEntity(postCategoryUserDTO, postEntity);
            Category categoryEntity = categoryRepository.findById(postCategoryUserDTO.getCategory().getId()).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
            categoryRepository.save(categoryEntity);
            User userEntity = userRepository.findById(postCategoryUserDTO.getUser().getId()).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
            userRepository.save(userEntity);
            postEntity.setCategory(categoryEntity);
            postEntity.setUser(userEntity);
            postEntity = postRepository.save(postEntity);
            return new PostCategoryUserDTO(postEntity);
        } catch (ConstraintViolationException e) {
            throw new ResourceNotFoundException("E-mail já cadastrado");
        }
    }

    private void copyDtoToEntity(PostCategoryUserDTO postCategoryUserDTO, Post postEntity) {
        LocalDateTime today = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        postEntity.setTitle(postCategoryUserDTO.getTitle());
        postEntity.setTitleDescription(postCategoryUserDTO.getTitleDescription());
        postEntity.setDescription(postCategoryUserDTO.getDescription());
        postEntity.setDateTime(today);

    }
}
