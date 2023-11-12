package goorm.board.dto.request;

import goorm.board.entity.Board;
import goorm.board.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardSaveRequest {
    private String title;
    private String content;

    public Board toEntity() {
        Board board = new Board(this.title, this.content);
        return board;
    }
}
