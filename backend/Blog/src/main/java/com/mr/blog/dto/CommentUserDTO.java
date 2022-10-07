package com.mr.blog.dto;

import com.mr.blog.entities.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public class CommentUserDTO {
    private Long id;
    @Size(min = 5, max = 200, message = "Descrição do título precisar ter mínimo 5 e máximo 200 caracteres")
    @NotBlank(message = "Campo requerido")
    private String commentDescription;
    private Instant dateTime;
    private UserDTO user;


    public CommentUserDTO(Long id, String commentDescription, Instant dateTime, UserDTO user) {
        this.id = id;
        this.commentDescription = commentDescription;
        this.dateTime = dateTime;
        this.user = user;
    }

    public CommentUserDTO(Comment commentEntity) {
        id = commentEntity.getId();
        commentDescription = commentEntity.getCommentDescription();
        dateTime = commentEntity.getDateTime();
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

    public UserDTO getUser() {
        return user;
    }
}
