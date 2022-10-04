package com.mr.blog.dto;

import com.mr.blog.entities.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class PostCategoryUserDTO {
    private Long id;
    @Size(min = 10, max = 30, message = "Título precisar ter mínimo 10 e máximo 30 caracteres")
    @NotBlank(message = "Campo requerido")
    private String title;
    @Size(min = 10, max = 50, message = "Título descrição precisar ter mínimo 10 e máximo 50 caracteres")
    @NotBlank(message = "Campo requerido")
    private String titleDescription;
    @Size(min = 20, max = 30, message = "Descrição precisar ter mínimo 50 caracteres")
    @NotBlank(message = "Campo requerido")
    private String description;
    private LocalDateTime dateTime;
    private CategoryDTO category;
    private UserDTO user;

    public PostCategoryUserDTO(Long id, String title, String titleDescription, String description, LocalDateTime dateTime, CategoryDTO category, UserDTO user) {
        this.id = id;
        this.title = title;
        this.titleDescription = titleDescription;
        this.description = description;
        this.dateTime = dateTime;
        this.category = category;
        this.user = user;
    }

    public PostCategoryUserDTO(Post postEntity) {
        id = postEntity.getId();
        title = postEntity.getTitle();
        titleDescription = postEntity.getTitleDescription();
        dateTime = postEntity.getDateTime();
        category = new CategoryDTO(postEntity.getCategory());
        user = new UserDTO(postEntity.getUser());
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public UserDTO getUser() {
        return user;
    }
}
