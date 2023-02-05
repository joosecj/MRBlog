package com.mr.blog.controllers;

import com.mr.blog.dto.v1.CommentDTO;
import com.mr.blog.dto.v1.CommentPostUserDTO;
import com.mr.blog.services.CommentService;
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
@RequestMapping(value = "/api/comments/v1")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Operation(summary = "Find By Id Comment", description = "Find By Id Comment",
            tags = {"Comment"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(schema = @Schema(implementation = CommentPostUserDTO.class))
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
    public ResponseEntity<CommentPostUserDTO> findById(@PathVariable Long id) {
        CommentPostUserDTO commentPostUserDTO = commentService.findById(id);
        return ResponseEntity.ok(commentPostUserDTO);
    }

    @Operation(summary = "Find All Comments", description = "Find By Id Comment",
            tags = {"Comment"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(schema = @Schema(implementation = CommentPostUserDTO.class))
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
    public ResponseEntity<Page<CommentPostUserDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(commentService.findAll(pageable));
    }


    @Operation(summary = "Create Comment and By Category Id and By User Id", description = "Create Comment and By Category and By User Id and By User Id",
            tags = {"Comment"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(schema = @Schema(implementation = CommentPostUserDTO.class))
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
    @PostMapping
    public ResponseEntity<CommentPostUserDTO> insert(@RequestBody @Valid CommentPostUserDTO commentPostUserDTO) {
        commentPostUserDTO = commentService.insert(commentPostUserDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(commentPostUserDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(commentPostUserDTO);
    }

    @Operation(summary = "Update Comment", description = "Update Comment",
            tags = {"Comment"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(schema = @Schema(implementation = CommentPostUserDTO.class))
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
    public ResponseEntity<CommentDTO> update(@PathVariable Long id, @Valid @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.update(id, commentDTO));
    }

    @Operation(summary = "Delete By Id Comment", description = "Delete By Id Comment",
            tags = {"Comment"},
            responses = {
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = {
                                    @Content(schema = @Schema(implementation = CommentPostUserDTO.class))
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
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
