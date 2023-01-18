package com.gustavo.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.demo.data.vo.v1.BookVO;
import com.gustavo.demo.services.BookServices;
import com.gustavo.demo.util.CustomMediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Books", description = "Endpoints for Managing Books")
public class BookController {


    @Autowired
    private BookServices service;

    @GetMapping(
        produces = {
            CustomMediaType.APPLICATION_JSON,
            CustomMediaType.APPLICATION_XML,
            CustomMediaType.APPLICATION_YML
        }
    )
    @Operation(
        summary = "Finds all books",
        description = "Finds all books",
        tags = {"Books"},
        responses = {
            @ApiResponse(
                description = "success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = CustomMediaType.APPLICATION_JSON, 
                        array = @ArraySchema(schema = @Schema(implementation = BookVO.class))
                    )
                }
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public List<BookVO> findAll(){
        return service.findAll();
    }


    @GetMapping(
        value = "/{id}",
        produces = {
            CustomMediaType.APPLICATION_JSON, 
            CustomMediaType.APPLICATION_XML, 
            CustomMediaType.APPLICATION_YML
        }
    )
    @Operation(
        summary = "Finds a book",
        description = "Finds a specific book by it's Id passed as PathVariable.",
        tags = {"Books"},
        responses = {
            @ApiResponse(
                description = "success", 
                responseCode = "200", 
                content = @Content(schema = @Schema(implementation = BookVO.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public BookVO findBookByID(
        @PathVariable(value = "id") Integer id
    ){
        return service.findByID(id);
    } 

    @PostMapping(
        value = "",
        consumes = {
            CustomMediaType.APPLICATION_JSON, 
            CustomMediaType.APPLICATION_XML, 
            CustomMediaType.APPLICATION_YML
        },
        produces = {
            CustomMediaType.APPLICATION_JSON, 
            CustomMediaType.APPLICATION_XML, 
            CustomMediaType.APPLICATION_YML
        }
    )
    @Operation(
        summary = "Creates a book",
        description = "Creates a book by passing it's values throught payload",
        tags = {"Books"},
        responses = {
            @ApiResponse(
                description = "success", 
                responseCode = "200", 
                content = @Content(schema = @Schema(implementation = BookVO.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public BookVO create(@RequestBody BookVO book){
        return service.create(book);
    }

    @PutMapping(
        value = "",
        consumes = {
            CustomMediaType.APPLICATION_JSON, 
            CustomMediaType.APPLICATION_XML, 
            CustomMediaType.APPLICATION_YML
        },
        produces = {
            CustomMediaType.APPLICATION_JSON, 
            CustomMediaType.APPLICATION_XML, 
            CustomMediaType.APPLICATION_YML
        }
    )
    @Operation(
        summary = "Updates a book",
        description = "Updates a book by passing it's values throught payload",
        tags = {"Books"},
        responses = {
            @ApiResponse(
                description = "success", 
                responseCode = "200", 
                content = @Content(schema = @Schema(implementation = BookVO.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public BookVO update(@RequestBody BookVO book){
        return service.update(book);
    }

    @DeleteMapping(
        value = "/{id}"
    )
    @Operation(
        summary = "Deletes a book",
        description = "Deletes a book by passing it's Id as PathVariable",
        tags = {"Books"},
        responses = {
            @ApiResponse(description = "success", responseCode = "200", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public void delete(@PathVariable(value = "id") Integer id){
        service.delete(id);
    }
    
}
