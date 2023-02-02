package com.mr.blog.dto.v1;

import com.mr.blog.entities.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.Instant;

public class CommentPostUserDTO {
    private Long id;
    @Size(min = 5, max = 200, message = "Descrição do título precisar ter mínimo 5 e máximo 200 caracteres")
    @NotBlank(message = "Campo requerido")
    private String commentDescription;

    private Instant dateTime;
    private PostDTO post;
    private UserDTO user;

    public CommentPostUserDTO(Long id, String commentDescription, Instant dateTime, PostDTO post, UserDTO user) {
        this.id = id;
        this.commentDescription = commentDescription;
        this.dateTime = dateTime;
        this.post = post;
        this.user = user;
    }

    public CommentPostUserDTO(Comment commentEntity) {
        id = commentEntity.getId();
        commentDescription = commentEntity.getCommentDescription();
        dateTime = commentEntity.getDateTime();
        post = new PostDTO(commentEntity.getPost());
        user = new UserDTO(commentEntity.getUser());
    }

    public Long getId() {
        return id;
    }

    public String getCommentDescription() {
        return commentDescription;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public PostDTO getPost() {
        return post;
    }

    public UserDTO getUser() {
        return user;
    }
}
