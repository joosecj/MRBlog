package com.mr.blog.Controllers;

import com.mr.blog.dto.*;
import com.mr.blog.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostCategoryUserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @GetMapping(value = "/{id}/min")
    public ResponseEntity<PostDTO> findByIdMin(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findByIdMin(id));
    }

    @GetMapping
    public ResponseEntity<Page<PostCategoryUserDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(postService.findAll(pageable));
    }

    @GetMapping(value = "/{id}/comments")
    public ResponseEntity<List<CommentUserDTO>> findAccountByMovements(@PathVariable Long id) {
        List<CommentUserDTO> commentUserDTOS = postService.findPostByComments(id);
        return ResponseEntity.ok(commentUserDTOS);
    }

    @PostMapping
    public ResponseEntity<PostCategoryUserDTO> insert(@RequestBody @Valid PostCategoryUserDTO postCategoryUserDTO) {
        postCategoryUserDTO = postService.insert(postCategoryUserDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(postCategoryUserDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(postCategoryUserDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PostCategoryDTO> update(@PathVariable Long id, @Valid @RequestBody PostCategoryDTO postCategoryDTO) {
        return ResponseEntity.ok(postService.update(id, postCategoryDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
