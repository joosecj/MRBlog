package com.mr.blog.Controllers;

import com.mr.blog.dto.CommentPostUserDTO;
import com.mr.blog.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentPostUserDTO> insert(@RequestBody @Valid CommentPostUserDTO commentPostUserDTO) {
        commentPostUserDTO = commentService.insert(commentPostUserDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(commentPostUserDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(commentPostUserDTO);
    }
}
