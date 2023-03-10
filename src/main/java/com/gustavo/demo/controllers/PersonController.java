package com.gustavo.demo.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.demo.data.vo.v1.PersonVO;
import com.gustavo.demo.services.PersonServices;
import com.gustavo.demo.util.CustomMediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {

    // private PersonServices service = new PersonServices();
    @Autowired
    private PersonServices service;

    @GetMapping(
        produces = {CustomMediaType.APPLICATION_JSON, CustomMediaType.APPLICATION_XML, CustomMediaType.APPLICATION_YML}
    )
    @Operation(
        summary = "Finds all People", 
        description = "Finds all People", 
        tags = {"People"},
        responses = {
            @ApiResponse(
                description = "success", 
                responseCode = "200", 
                content = {
                    @Content(
                        mediaType = CustomMediaType.APPLICATION_JSON, 
                        array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)))
                }
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public List<PersonVO> findAll() throws Exception  {
        return service.findAll();
    }

    @GetMapping(
        value = "/{id}", 
        produces = {CustomMediaType.APPLICATION_JSON, CustomMediaType.APPLICATION_XML, CustomMediaType.APPLICATION_YML}
    )
    @Operation(
        summary = "Finds a Person", 
        description = "Finds a Person",
        tags = {"Person"},
        responses = {
            @ApiResponse(
                description = "success", 
                responseCode = "200", 
                content = @Content(schema = @Schema(implementation = PersonVO.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public PersonVO findById( @PathVariable(value = "id") Long id ) throws Exception  {
        return service.findByID(id);
    }    

    @PostMapping(
        value = "",
        consumes = {CustomMediaType.APPLICATION_JSON, CustomMediaType.APPLICATION_XML, CustomMediaType.APPLICATION_YML}, 
        produces = {CustomMediaType.APPLICATION_JSON, CustomMediaType.APPLICATION_XML, CustomMediaType.APPLICATION_YML}
    )
    @Operation(
        summary = "Creates a Person", 
        description = "Creates a Person by passing the PersonVO in the message body", 
        tags = {"Person"},
        responses = {
            @ApiResponse(
                description = "success",
                responseCode = "200", 
                content = @Content(schema = @Schema(implementation = PersonVO.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
        }
    )
    public PersonVO create(@RequestBody PersonVO person) throws Exception  {
        return service.create(person);
    }

    @PutMapping(
        value = "",
        consumes = {CustomMediaType.APPLICATION_JSON, CustomMediaType.APPLICATION_XML, CustomMediaType.APPLICATION_YML}, 
        produces = {CustomMediaType.APPLICATION_JSON, CustomMediaType.APPLICATION_XML, CustomMediaType.APPLICATION_YML}
    )
    @Operation(
        summary = "Updates a Person", 
        description = "Updates a Person by passing the PersonVO in the message body", 
        tags = {"Person"},
        responses = {
            @ApiResponse(
                description = "success", 
                responseCode = "200", 
                content = @Content(schema = @Schema(implementation = PersonVO.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
        }
    )
    public PersonVO update(@RequestBody PersonVO person) throws Exception  {
        return service.update(person);
    }

    @DeleteMapping(
        value = "/{id}"
    )
    @Operation(
        summary = "Deletes a Person", 
        description = "Deletes a Person by passing the Person id throught PathVariable", 
        tags = {"Person"},
        responses = {
            @ApiResponse(description = "No content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
        }
    )
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception  {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
