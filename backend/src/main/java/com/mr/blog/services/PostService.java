package com.mr.blog.services;

import com.mr.blog.dto.v1.CommentUserDTO;
import com.mr.blog.dto.v1.PostCategoryDTO;
import com.mr.blog.dto.v1.PostCategoryUserDTO;
import com.mr.blog.dto.v1.PostDTO;
import com.mr.blog.entities.Category;
import com.mr.blog.entities.Comment;
import com.mr.blog.entities.Post;
import com.mr.blog.entities.User;
import com.mr.blog.repositories.CategoryRepository;
import com.mr.blog.repositories.PostRepository;
import com.mr.blog.repositories.UserRepository;
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

import java.time.Instant;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public PostCategoryUserDTO findById(long id) {
        Post postEntity = postRepository.findById(id).orElseThrow(PostService::postNotFound);
        return new PostCategoryUserDTO(postEntity);
    }

    @Transactional(readOnly = true)
    public PostDTO findByIdMin(long id) {
        Post postEntity = postRepository.findById(id).orElseThrow(PostService::postNotFound);
        return new PostDTO(postEntity);
    }

    @Transactional(readOnly = true)
    public Page<PostCategoryUserDTO> findAll(Pageable pageable) {
        Page<Post> postPage = postRepository.findAll(pageable);
        return postPage.map(PostCategoryUserDTO::new);
    }

    @Transactional(readOnly = true)
    public List<CommentUserDTO> findPostByComments(Long id) {
        Post postEntity = postRepository.findById(id).orElseThrow(PostService::postNotFound);
        List<Comment> commentPage = postEntity.getCommentList();
        return commentPage.stream().map(CommentUserDTO::new).toList();
    }


    public PostCategoryUserDTO insert(PostCategoryUserDTO postCategoryUserDTO) {
        try {
            Post postEntity = new Post();
            copyDtoToEntity(postCategoryUserDTO, postEntity);
            Long idCategory = postCategoryUserDTO.getCategory().getId();
            Category categoryEntity = categoryRepository.findById(idCategory).orElseThrow(()
                    -> new ResourceNotFoundException("Category not found"));
            categoryRepository.save(categoryEntity);
            User userEntity = userRepository.findById(postCategoryUserDTO.getUser().getId()).orElseThrow(PostService::userNotFound);
            userRepository.save(userEntity);
            postEntity.setCategory(categoryEntity);
            postEntity.setUser(userEntity);
            postEntity = postRepository.save(postEntity);
            return new PostCategoryUserDTO(postEntity);
        } catch (ConstraintViolationException e) {
            throw new ResourceNotFoundException("E-mail já cadastrado");
        }
    }

    public PostCategoryDTO update(Long id, PostCategoryDTO postCategoryDTO) throws ResourceNotFoundException {
        try {
            Post postEntity = postRepository.getReferenceById(id);
            postEntity.setTitle(postCategoryDTO.getTitle());
            postEntity.setTitleDescription(postCategoryDTO.getTitleDescription());
            postEntity.setDescription(postCategoryDTO.getDescription());
            postEntity.setDateTime(Instant.now());
            Category categoryEntity = categoryRepository.findById(postCategoryDTO.getCategory().getId())
                    .orElseThrow(PostService::categoryNotFound);
            postEntity.setCategory(categoryRepository.save(categoryEntity));
            return new PostCategoryDTO(postRepository.save(postEntity));
        } catch (NullPointerException e) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Post não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        try {
            postRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("User not found");
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Referential integrity failure");
        }
    }

    private void copyDtoToEntity(PostCategoryUserDTO postCategoryUserDTO, Post postEntity) {
        postEntity.setTitle(postCategoryUserDTO.getTitle());
        postEntity.setTitleDescription(postCategoryUserDTO.getTitleDescription());
        postEntity.setDescription(postCategoryUserDTO.getDescription());
        postEntity.setDateTime(Instant.now());
    }

    private static ResourceNotFoundException userNotFound() {
        return new ResourceNotFoundException("User not found");
    }

    private static ResourceNotFoundException postNotFound() {
        return new ResourceNotFoundException("Post not found");
    }

    private static ResourceNotFoundException categoryNotFound() {
        return new ResourceNotFoundException("Category not found");
    }
}
