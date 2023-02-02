package com.mr.blog.dto.v1;

import com.mr.blog.entities.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryDTO {
    private Long id;
    @Size(min = 3, max = 40, message = "Nome precisar ter mínimo 3 e máximo 40 caracteres")
    @NotBlank(message = "Campo requerido")
    private String name;
    @Size(min = 10, max = 50, message = "Descrição precisar ter mínimo 10 e máximo 50 caracteres")
    @NotBlank(message = "Campo requerido")
    private String description;

    public CategoryDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public CategoryDTO(Category categoryEntity) {
        id = categoryEntity.getId();
        name = categoryEntity.getName();
        description = categoryEntity.getDescription();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
