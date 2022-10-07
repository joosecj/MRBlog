package com.mr.blog.services;

import com.mr.blog.dto.CommentDTO;
import com.mr.blog.dto.CommentPostUserDTO;
import com.mr.blog.entities.Comment;
import com.mr.blog.entities.Post;
import com.mr.blog.entities.User;
import com.mr.blog.repositories.CommentRepository;
import com.mr.blog.repositories.PostRepository;
import com.mr.blog.repositories.UserRepository;
import com.mr.blog.services.exeptions.DataBaseException;
import com.mr.blog.services.exeptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public CommentPostUserDTO findById(long id) {
        Comment commentEntity = commentRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Comentário não encontrado"));
        return new CommentPostUserDTO(commentEntity);
    }

    @Transactional(readOnly = true)
    public Page<CommentPostUserDTO> findAll(Pageable pageable) {
        Page<Comment> commentPage = commentRepository.findAll(pageable);
        return commentPage.map(CommentPostUserDTO::new);
    }

    @Transactional(readOnly = false)
    public CommentPostUserDTO insert(CommentPostUserDTO commentPostUserDTO) {
            Comment commentEntity = new Comment();
            copyDtoToEntity(commentPostUserDTO, commentEntity);
            Post postEntity = postRepository.findById(commentPostUserDTO.getPost().getId()).orElseThrow(()
                    -> new ResourceNotFoundException("Post não encontrado"));
            postRepository.save(postEntity);
            User userEntity = userRepository.findById(commentPostUserDTO.getUser().getId()).orElseThrow(()
                    -> new ResourceNotFoundException("Usuário não encontrado"));
            userRepository.save(userEntity);
            commentEntity.setPost(postEntity);
            commentEntity.setUser(userEntity);
            commentEntity = commentRepository.save(commentEntity);
            return new CommentPostUserDTO(commentEntity);
    }

    @Transactional(readOnly = false)
    public CommentDTO update(Long id, CommentDTO commentDTO) {
        try {
            Comment commentEntity = commentRepository.getReferenceById(id);
            commentEntity.setCommentDescription(commentDTO.getCommentDescription());
            commentEntity.setDateTime(Instant.now());
            return new CommentDTO(commentRepository.save(commentEntity));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Post não encontrado");
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        try {
            commentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Falha de integridade refencial");
        }
    }

    private void copyDtoToEntity(CommentPostUserDTO commentPostUserDTO, Comment commentEntity) {
        commentEntity.setCommentDescription(commentPostUserDTO.getCommentDescription());
        commentEntity.setDateTime(Instant.now());
    }
}
