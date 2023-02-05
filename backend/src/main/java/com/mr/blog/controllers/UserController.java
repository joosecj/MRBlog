package com.mr.blog.controllers;

import com.mr.blog.dto.v1.UserDTO;
import com.mr.blog.services.UserService;
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
@RequestMapping(value = "/api/users/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Find By Id User", description = "Find By Id User",
            tags = "User",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(schema = @Schema(implementation = UserDTO.class))
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
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Operation(summary = "Find All User", description = "Find All User",
            tags = "User",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(schema = @Schema(implementation = UserDTO.class))
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
    public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(userService.findAll(pageable));
    }

    @Operation(summary = "Create User", description = "Create User",
            tags = "User",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(schema = @Schema(implementation = UserDTO.class))
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
    public ResponseEntity<UserDTO> inset(@Valid @RequestBody UserDTO userDTO) {
        userDTO = userService.insert(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(userDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(userDTO);
    }

    @Operation(summary = "Update By Id", description = "Update All User",
            tags = "User",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(schema = @Schema(implementation = UserDTO.class))
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
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.update(id, userDTO));
    }

    @Operation(summary = "Delete By Id", description = "Delete By Id",
            tags = "User",
            responses = {
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = {
                                    @Content(schema = @Schema(implementation = UserDTO.class))
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
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
