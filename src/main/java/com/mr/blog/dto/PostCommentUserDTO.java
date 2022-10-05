package com.mr.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public class PostCommentUserDTO {
    private Long id;
    @Size(min = 10, max = 30, message = "Título precisar ter mínimo 10 e máximo 30 caracteres")
    @NotBlank(message = "Campo requerido")
    private String title;
    @Size(min = 10, max = 40, message = "Descrição do título precisar ter mínimo 10 e máximo 40 caracteres")
    @NotBlank(message = "Campo requerido")
    private String titleDescription;
    @Size(min = 20, message = "Descrição precisar ter mínimo 20 caracteres")
    @NotBlank(message = "Campo requerido")
    private String description;
    private Instant dateTime;
    private CommentDTO comment;
}
