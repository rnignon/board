package goorm.board.dto.response;

import goorm.board.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private String content;
    private UserResponse author;

    public static CommentResponse toDto (Comment comment) {
        return new CommentResponse(comment.getId(), comment.getContent(), UserResponse.toDto(comment.getAuthor()));
    }
}
