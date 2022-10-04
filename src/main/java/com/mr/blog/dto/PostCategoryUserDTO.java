package com.mr.blog.dto;

import com.mr.blog.entities.Category;
import com.mr.blog.entities.Post;
import com.mr.blog.entities.User;

import java.time.LocalDateTime;

public class PostCategoryUserDTO {
    private Long id;
    private String title;
    private String titleDescription;
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
