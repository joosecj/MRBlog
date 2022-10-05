package com.mr.blog.Controllers;

import com.mr.blog.dto.CommentPostUserDTO;
import com.mr.blog.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<Page<CommentPostUserDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(commentService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<CommentPostUserDTO> insert(@RequestBody @Valid CommentPostUserDTO commentPostUserDTO) {
        commentPostUserDTO = commentService.insert(commentPostUserDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(commentPostUserDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(commentPostUserDTO);
    }
}
