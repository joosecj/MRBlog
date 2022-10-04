package com.mr.blog.dto;

import com.mr.blog.entities.Post;

import java.time.LocalDateTime;

public class PostDTO {
    private Long id;
    private String title;
    private String titleDescription;
    private String description;
    private LocalDateTime dateTime;

    public PostDTO(Long id, String title, String titleDescription, String description, LocalDateTime dateTime) {
        this.id = id;
        this.title = title;
        this.titleDescription = titleDescription;
        this.description = description;
        this.dateTime = dateTime;
    }

    public PostDTO(Post postEntity) {
        id = postEntity.getId();
        title = postEntity.getTitle();
        titleDescription = postEntity.getTitleDescription();
        description = postEntity.getDescription();
        dateTime = postEntity.getDateTime();
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
}
