package goorm.board.service;

import goorm.board.DeleteStatus;
import goorm.board.dto.request.BoardSaveRequest;
import goorm.board.dto.request.BoardUpdateRequest;
import goorm.board.dto.response.BoardListResponse;
import goorm.board.dto.response.BoardResponse;
import goorm.board.entity.Board;
import goorm.board.repository.BoardRepository;
import goorm.board.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardResponse createBoard(Long userId, BoardSaveRequest request) {
        Board board = request.toEntity();
        board.setAuthor(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다")));
        return BoardResponse.toDto(boardRepository.save(board));
    }

    public List<BoardListResponse> searchBoardList(int page, int pageSize) {
        return boardRepository.findAllByDeleteStatus(
                DeleteStatus.ACTIVE,
                PageRequest.of(
                        page, pageSize, Sort.by(Sort.Direction.DESC, "id")
                )).map(BoardListResponse::toDto).toList();
    }

    public BoardResponse searchBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다"));
        return BoardResponse.toDto(board);
    }

    public BoardResponse updateBoard(BoardUpdateRequest request) {
        Board board = boardRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다"));

        board.update(request.getTitle(), request.getContent());
        return BoardResponse.toDto(board);
    }

    public String deleteBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다"));

        boardRepository.delete(board);
        return "OK";
    }
}
