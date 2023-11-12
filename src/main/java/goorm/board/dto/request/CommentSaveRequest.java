package goorm.board.dto.request;

import goorm.board.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentSaveRequest {
    private String content;

    public Comment toEntity() {
        return new Comment(this.content);
    }
}
