package com.mr.blog.controllers;

import com.mr.blog.dto.v1.*;
import com.mr.blog.services.PostService;
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
import java.util.List;

@RestController
@RequestMapping(value = "/api/posts/v1")
public class PostController {
    @Autowired
    private PostService postService;

    @Operation(summary = "Find By Id Post", description = "Find By Id Post",
            tags = "Post",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(schema = @Schema(implementation = PostCategoryDTO.class))
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
    public ResponseEntity<PostCategoryUserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @Operation(summary = "Find By Id Post and min info", description = "Find By Id Post and min info",
            tags = "Post",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(schema = @Schema(implementation = PostDTO.class))
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
    @GetMapping(value = "/{id}/min")
    public ResponseEntity<PostDTO> findByIdMin(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findByIdMin(id));
    }

    @Operation(summary = "Find All Post", description = "Find All Post",
            tags = "Post",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(schema = @Schema(implementation = PostCategoryUserDTO.class))
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
    public ResponseEntity<Page<PostCategoryUserDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(postService.findAll(pageable));
    }

    @Operation(summary = "Find By Id Post and Comments", description = "Find By Id Post and Comments",
            tags = "Post",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(schema = @Schema(implementation = CommentUserDTO.class))
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
    @GetMapping(value = "/{id}/comments")
    public ResponseEntity<List<CommentUserDTO>> findAccountByMovements(@PathVariable Long id) {
        List<CommentUserDTO> commentUserDTOS = postService.findPostByComments(id);
        return ResponseEntity.ok(commentUserDTOS);
    }

    @Operation(summary = "Create Post and Category Id and User Id", description = "Create Post and Category Id and User Id",
            tags = "Post",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(schema = @Schema(implementation = PostCategoryUserDTO.class))
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
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {
                            @Content(schema = @Schema(implementation = UnionType.class))
                    })
            }
    )
    @PostMapping
    public ResponseEntity<PostCategoryUserDTO> insert(@RequestBody @Valid PostCategoryUserDTO postCategoryUserDTO) {
        postCategoryUserDTO = postService.insert(postCategoryUserDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(postCategoryUserDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(postCategoryUserDTO);
    }

    @Operation(summary = "Update By Id", description = "Update By Id",
            tags = "Post",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(schema = @Schema(implementation = CommentUserDTO.class))
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
    public ResponseEntity<PostCategoryDTO> update(@PathVariable Long id, @Valid @RequestBody PostCategoryDTO postCategoryDTO) {
        return ResponseEntity.ok(postService.update(id, postCategoryDTO));
    }


    @Operation(summary = "Delete By Id", description = "Delete By Id",
            tags = "Post",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(schema = @Schema(implementation = CommentUserDTO.class))
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
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
