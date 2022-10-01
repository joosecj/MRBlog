package com.mr.blog.dto;

import com.mr.blog.entities.User;

import java.time.LocalDate;

public class UserDTO {
    private Long id;
    private String name;
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
