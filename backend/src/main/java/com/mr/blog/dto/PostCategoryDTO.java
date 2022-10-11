package com.mr.blog.dto;

import com.mr.blog.entities.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public class PostCategoryDTO {
    private Long id;
    @Size(min = 10, max = 80, message = "Título precisar ter mínimo 10 e máximo 80 caracteres")
    @NotBlank(message = "Campo requerido")
    private String title;
    @Size(min = 10, max = 100, message = "Descrição do título precisar ter mínimo 10 e máximo 100 caracteres")
    @NotBlank(message = "Campo requerido")
    private String titleDescription;
    @Size(min = 20, message = "Descrição precisar ter mínimo 20 caracteres")
    @NotBlank(message = "Campo requerido")
    private String description;
    private Instant dateTime;
    private CategoryDTO category;


    public PostCategoryDTO(Long id, String title, String titleDescription, String description, Instant dateTime, CategoryDTO category) {
        this.id = id;
        this.title = title;
        this.titleDescription = titleDescription;
        this.description = description;
        this.dateTime = dateTime;
        this.category = category;
    }

    public PostCategoryDTO(Post postEntity) {
        id = postEntity.getId();
        title = postEntity.getTitle();
        titleDescription = postEntity.getTitleDescription();
        description = postEntity.getDescription();
        dateTime = postEntity.getDateTime();
        category = new CategoryDTO(postEntity.getCategory());
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleDescription() {
        return titleDescription;
    }

    public String getDescription() {
        return description;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public CategoryDTO getCategory() {
        return category;
    }

}
