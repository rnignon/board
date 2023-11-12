package goorm.board.controller;

import goorm.board.dto.request.CommentSaveRequest;
import goorm.board.dto.request.CommentUpdateRequest;
import goorm.board.dto.response.CommentResponse;
import goorm.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public CommentResponse createComment(
            @RequestParam Long boardId,
            @RequestParam Long userId,
            @RequestBody CommentSaveRequest request
            ) {
        return commentService.createComment(userId, boardId, request);
    }

    @GetMapping("/{boardId}")
    public List<CommentResponse> searchComments(
            @RequestParam int page,
            @PathVariable Long boardId
            ) {
        return commentService.searchComments(page, boardId);
    }

    @PatchMapping
    public CommentResponse updateComment(
            @RequestBody CommentUpdateRequest request
            ) {
        return commentService.updateComment(request);
    }

    @DeleteMapping("/{commentId}")
    public String deleteComment(
            @PathVariable Long commentId
            ) {
        return commentService.deleteComment(commentId);
    }
}
