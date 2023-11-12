package goorm.board.dto.response;

import goorm.board.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String username;
    public static UserResponse toDto(User author) {
        return new UserResponse(author.getUsername());
    }
}
