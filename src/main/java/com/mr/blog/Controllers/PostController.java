package com.mr.blog.Controllers;

import com.mr.blog.dto.PostCategoryUserDTO;
import com.mr.blog.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<Page<PostCategoryUserDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(postService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<PostCategoryUserDTO> insert(@RequestBody @Valid PostCategoryUserDTO postCategoryUserDTO) {
        postCategoryUserDTO = postService.insert(postCategoryUserDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(postCategoryUserDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(postCategoryUserDTO);
    }
}
