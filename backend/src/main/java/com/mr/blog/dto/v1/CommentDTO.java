package com.mr.blog.dto.v1;

import com.mr.blog.entities.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public class CommentDTO {
    private Long id;
    @Size(min = 5, max = 200, message = "Descrição do título precisar ter mínimo 5 e máximo 200 caracteres")
    @NotBlank(message = "Campo requerido")
    private String commentDescription;
    private Instant dateTime;

    public CommentDTO(Long id, String commentDescription, Instant dateTime) {
        this.id = id;
        this.commentDescription = commentDescription;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public CommentDTO(Comment commentEntity) {
        id = commentEntity.getId();
        commentDescription = commentEntity.getCommentDescription();
        dateTime = commentEntity.getDateTime();
    }

    public String getCommentDescription() {
        return commentDescription;
    }

    public Instant getDateTime() {
        return dateTime;
    }
}
