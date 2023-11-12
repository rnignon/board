package goorm.board.service;

import goorm.board.DeleteStatus;
import goorm.board.dto.request.CommentSaveRequest;
import goorm.board.dto.request.CommentUpdateRequest;
import goorm.board.dto.response.BoardListResponse;
import goorm.board.dto.response.CommentResponse;
import goorm.board.entity.Board;
import goorm.board.entity.Comment;
import goorm.board.repository.BoardRepository;
import goorm.board.repository.CommentRepository;
import goorm.board.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public CommentResponse createComment(Long userId, Long boardId, CommentSaveRequest request) {
        Comment comment = request.toEntity();
        comment.setAuthor(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다")));
        comment.setBoard(boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다")));

        return CommentResponse.toDto(commentRepository.save(comment));
    }

    public List<CommentResponse> searchComments(int page, Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다"));
        List<CommentResponse> comments = commentRepository.findAllByDeleteStatus(
                DeleteStatus.ACTIVE,
                PageRequest.of(
                        page, 10))
                .map(CommentResponse::toDto).toList();
        return comments;
    }

    public CommentResponse updateComment(CommentUpdateRequest request) {
        Comment comment = commentRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 댓글입니다"));

        comment.update(request.getContent());
        return CommentResponse.toDto(comment);
    }

    public String deleteComment (Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 댓글입니다"));

        commentRepository.delete(comment);
        return "OK";
    }
}
