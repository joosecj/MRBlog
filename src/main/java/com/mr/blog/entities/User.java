package com.mr.blog.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 60)
    private String name;
    @Column(unique = true)
    private String email;
    private LocalDate birthDate;
    private String urlImage;

    @OneToMany(mappedBy = "user")
    private List<Post> post = new ArrayList<>() ;

    public User() {
    }

    public User(Long id, String name, String email, LocalDate birthDate, String urlImage) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.urlImage = urlImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public List<Post> getPost() {
        return post;
    }
}
