package org.unibl.etf.webshopapplication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.webshopapplication.model.AddCommentRequest;
import org.unibl.etf.webshopapplication.model.CommentResponse;
import org.unibl.etf.webshopapplication.services.CommentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add-comment")
    public void addComment(@RequestBody @Valid AddCommentRequest addCommentRequest) {
        commentService.addComment(addCommentRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CommentResponse>> getAllCommentsForProduct(@PathVariable("id") int productId) {
        List<CommentResponse> responses = commentService.getAllCommentsForProduct(productId);
        return ResponseEntity.ok(responses);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable("id") int id) {
        commentService.deleteComment(id);
    }
}
