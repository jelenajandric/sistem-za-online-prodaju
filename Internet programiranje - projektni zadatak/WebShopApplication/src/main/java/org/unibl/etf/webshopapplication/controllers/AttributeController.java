package org.unibl.etf.webshopapplication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.webshopapplication.services.AttributeService;

import java.util.List;

@RestController
@RequestMapping("/attributes")
public class AttributeController {
    private final AttributeService attributeService;

    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<List<String>> getAllAttributesByCategoryId(@PathVariable("categoryId") int categoryId) {
        return ResponseEntity.ok(attributeService.getAllAttributesByCategoryId(categoryId));
    }
}
