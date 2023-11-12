package goorm.board.dto.response;

import goorm.board.entity.Board;
import goorm.board.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardListResponse {
    private Long id;
    private String title;
    private UserResponse author;

    public static BoardListResponse toDto (Board board) {
        return new BoardListResponse(board.getId(), board.getTitle(), UserResponse.toDto(board.getAuthor()));
    }
}
