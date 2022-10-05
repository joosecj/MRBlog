package com.mr.blog.dto;

import com.mr.blog.entities.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CommentDTO {
    private Long id;
    @Size(min = 5, max = 200, message = "Descrição do título precisar ter mínimo 5 e máximo 200 caracteres")
    @NotBlank(message = "Campo requerido")
    private String commentDescription;

    public CommentDTO(Long id, String commentDescription) {
        this.id = id;
        this.commentDescription = commentDescription;
    }

    public CommentDTO(Comment commentEntity) {
        id = commentEntity.getId();
        commentDescription = commentEntity.getCommentDescription();
    }

    public Long getId() {
        return id;
    }

    public String getCommentDescription() {
        return commentDescription;
    }
}
