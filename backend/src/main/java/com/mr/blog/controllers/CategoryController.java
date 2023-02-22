package com.mr.blog.controllers;

import com.mr.blog.dto.v1.CategoryDTO;
import com.mr.blog.services.CategoryServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.lang.model.type.UnionType;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/categories/v1")
public class CategoryController {
  private final CategoryServices categoryServices;
  @Autowired
  public CategoryController(CategoryServices categoryServices) {
    this.categoryServices = categoryServices;
  }

  @Operation(summary = "Find By Id Category", description = "Find By Id Category",
          tags = "Category",
          responses = {
                  @ApiResponse(
                          description = "Success",
                          responseCode = "200",
                          content = {
                                  @Content(schema = @Schema(implementation = CategoryDTO.class))
                          }
                  ),
                  @ApiResponse(description = "No Content", responseCode = "204", content = {
                          @Content(schema = @Schema(implementation = UnionType.class))
                  }),
                  @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                          @Content(schema = @Schema(implementation = UnionType.class))
                  }),
                  @ApiResponse(description = "Unauthorized", responseCode = "401", content = {
                          @Content(schema = @Schema(implementation = UnionType.class))
                  }),
                  @ApiResponse(description = "Not Found", responseCode = "404", content = {
                          @Content(schema = @Schema(implementation = UnionType.class))
                  }),
                  @ApiResponse(description = "Internal Error", responseCode = "500", content = {
                          @Content(schema = @Schema(implementation = UnionType.class))
                  })
          }
  )
  @GetMapping(value = "/{id}")
  public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
    return ResponseEntity.ok(categoryServices.findById(id));
  }

  @Operation(summary = "Find All Category", description = "Find All Category",
          tags = "Category",
          responses = {
                  @ApiResponse(
                          description = "Success",
                          responseCode = "200",
                          content = {
                                  @Content(schema = @Schema(implementation = CategoryDTO.class))
                          }
                  ),
                  @ApiResponse(description = "No Content", responseCode = "204", content = {
                          @Content(schema = @Schema(implementation = UnionType.class))
                  }),
                  @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                          @Content(schema = @Schema(implementation = UnionType.class))
                  }),
                  @ApiResponse(description = "Unauthorized", responseCode = "401", content = {
                          @Content(schema = @Schema(implementation = UnionType.class))
                  }),
                  @ApiResponse(description = "Not Found", responseCode = "404", content = {
                          @Content(schema = @Schema(implementation = UnionType.class))
                  }),
                  @ApiResponse(description = "Internal Error", responseCode = "500", content = {
                          @Content(schema = @Schema(implementation = UnionType.class))
                  })
          }
  )
  @GetMapping
  public ResponseEntity<Page<CategoryDTO>> findAll(Pageable pageable) {
    return ResponseEntity.ok(categoryServices.findAll(pageable));
  }

  @Operation(summary = "Create Category", description = "Create Category",
          tags = "Category",
          responses = {
                  @ApiResponse(
                          description = "Success",
                          responseCode = "200",
                          content = {
                                  @Content(schema = @Schema(implementation = CategoryDTO.class))
                          }
                  ),
                  @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                          @Content(schema = @Schema(implementation = UnionType.class))
                  }),
                  @ApiResponse(description = "Unauthorized", responseCode = "401", content = {
                          @Content(schema = @Schema(implementation = UnionType.class))
                  }),
                  @ApiResponse(description = "Internal Error", responseCode = "500", content = {
                          @Content(schema = @Schema(implementation = UnionType.class))
                  }),
          }
  )
  @PostMapping
  public ResponseEntity<CategoryDTO> inset(@Valid @RequestBody CategoryDTO categoryDTO) {
    categoryDTO = categoryServices.insert(categoryDTO);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(categoryDTO.getId()).toUri();
    return ResponseEntity.created(uri).body(categoryDTO);
  }

  @Operation(summary = "Update By Id Category", description = "Update By Id Category",
          tags = "Category",
          responses = {
                  @ApiResponse(
                          description = "Success",
                          responseCode = "200",
                          content = {
                                  @Content(schema = @Schema(implementation = CategoryDTO.class))
                          }
                  ),
                  @ApiResponse(description = "No Content", responseCode = "204", content = {
                          @Content(schema = @Schema(implementation = UnionType.class))
                  }),
                  @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                          @Content(schema = @Schema(implementation = UnionType.class))
                  }),
                  @ApiResponse(description = "Unauthorized", responseCode = "401", content = {
                          @Content(schema = @Schema(implementation = UnionType.class))
                  }),
                  @ApiResponse(description = "Not Found", responseCode = "404", content = {
                          @Content(schema = @Schema(implementation = UnionType.class))
                  }),
                  @ApiResponse(description = "Internal Error", responseCode = "500", content = {
                          @Content(schema = @Schema(implementation = UnionType.class))
                  })
          }
  )
  @PutMapping(value = "/{id}")
  public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @Valid @RequestBody CategoryDTO categoryDTO) {
    return ResponseEntity.ok(categoryServices.update(id, categoryDTO));
  }

  @Operation(summary = "Delete By Id Category", description = "Delete By Id Category",
          tags = "Category",
          responses = {
                  @ApiResponse(
                          description = "No Content",
                          responseCode = "204",
                          content = {
                                  @Content(schema = @Schema(implementation = CategoryDTO.class))
                          }
                  ),
                  @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                          @Content(schema = @Schema(implementation = UnionType.class))
                  }),
                  @ApiResponse(description = "Unauthorized", responseCode = "401", content = {
                          @Content(schema = @Schema(implementation = UnionType.class))
                  }),
                  @ApiResponse(description = "Not Found", responseCode = "404", content = {
                          @Content(schema = @Schema(implementation = UnionType.class))
                  }),
                  @ApiResponse(description = "Internal Error", responseCode = "500", content = {
                          @Content(schema = @Schema(implementation = UnionType.class))
                  })
          }
  )
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    categoryServices.delete(id);
    return ResponseEntity.noContent().build();
  }
}
