package goorm.board.dto.response;

import goorm.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponse {
    private Long id;
    private String title;
    private String content;
    private UserResponse author;

    public static BoardResponse toDto (Board board) {
        System.out.println("board = " + board);
        System.out.println(" + board.getAuthor() = " + board.getAuthor());
        return new BoardResponse(board.getId(), board.getTitle(), board.getContent(), UserResponse.toDto(board.getAuthor()));
    }
}
