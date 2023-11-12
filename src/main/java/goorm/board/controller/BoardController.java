package goorm.board.controller;

import goorm.board.dto.request.BoardSaveRequest;
import goorm.board.dto.request.BoardUpdateRequest;
import goorm.board.dto.response.BoardListResponse;
import goorm.board.dto.response.BoardResponse;
import goorm.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public BoardResponse createBoard(
            @RequestParam Long userId,
            @RequestBody BoardSaveRequest request
            ) {
        return boardService.createBoard(userId, request);
    }

    @GetMapping("/{id}")
    public BoardResponse searchBoard(
            @PathVariable Long id
            ) {
        return boardService.searchBoard(id);
    }

    @GetMapping("/list")
    public List<BoardListResponse> searchBoardList(
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize
            ) {
        return boardService.searchBoardList(page, pageSize);
    }

    @PatchMapping
    public BoardResponse updateBoard(
            @RequestBody BoardUpdateRequest request
            ) {
        return boardService.updateBoard(request);
    }

    @DeleteMapping("/{id}")
    public String deleteBoard(
            @PathVariable Long id
            ) {
        return boardService.deleteBoard(id);
    }
}
