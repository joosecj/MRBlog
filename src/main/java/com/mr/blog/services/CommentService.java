package com.mr.blog.services;

import com.mr.blog.dto.CommentPostUserDTO;
import com.mr.blog.entities.Comment;
import com.mr.blog.entities.Post;
import com.mr.blog.entities.User;
import com.mr.blog.repositories.CommentRepository;
import com.mr.blog.repositories.PostRepository;
import com.mr.blog.repositories.UserRepository;
import com.mr.blog.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Transactional(readOnly = false)
    public CommentPostUserDTO insert(CommentPostUserDTO commentPostUserDTO) {
            Comment commentEntity = new Comment();
            copyDtoToEntity(commentPostUserDTO, commentEntity);
            Post postEntity = postRepository.findById(commentPostUserDTO.getPost().getId()).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
            postRepository.save(postEntity);
            User userEntity = userRepository.findById(commentPostUserDTO.getUser().getId()).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
            userRepository.save(userEntity);
            commentEntity.setPost(postEntity);
            commentEntity.setUser(userEntity);
            commentEntity = commentRepository.save(commentEntity);
            return new CommentPostUserDTO(commentEntity);
    }

    private void copyDtoToEntity(CommentPostUserDTO commentPostUserDTO, Comment commentEntity) {
        commentEntity.setCommentDescription(commentPostUserDTO.getCommentDescription());
        commentEntity.setDateTime(Instant.now());
    }
}
