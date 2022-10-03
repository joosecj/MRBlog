package com.mr.blog.Controllers;

import com.mr.blog.dto.CategoryDTO;
import com.mr.blog.services.CategoryServices;
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
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private CategoryServices categoryServices;

    @PostMapping("/news")
    public ResponseEntity<CategoryDTO> inset(@Valid @RequestBody CategoryDTO categoryDTO) {
        categoryDTO = categoryServices.insert(categoryDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(categoryDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(categoryDTO);
    }
}
