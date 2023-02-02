package com.mr.blog.dto.v1;

import com.mr.blog.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UserDTO {
    private Long id;
    @Size(min = 3, max = 40, message = "Nome precisar ter mínimo 3 e máximo 40 caracteres")
    @NotBlank(message = "Campo requerido")
    private String name;
    @Email(message = "Email inválido")
    @NotBlank(message = "Campo requerido")
    private String email;
    private LocalDate birthDate;
    private String urlImage;
    private LocalDate registrationDate;

    public UserDTO(Long id, String name, String email, LocalDate birthDate, String urlImage, LocalDate registrationDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.urlImage = urlImage;
        this.registrationDate = registrationDate;
    }

    public UserDTO(User userEntity) {
        id = userEntity.getId();
        name = userEntity.getName();
        email = userEntity.getEmail();
        birthDate = userEntity.getBirthDate();
        urlImage = userEntity.getUrlImage();
        registrationDate = userEntity.getRegistrationDate();

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
}
