package goorm.board.service;

import goorm.board.dto.request.UserRequest;
import goorm.board.dto.response.UserResponse;
import goorm.board.entity.User;
import goorm.board.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse createUser(UserRequest request) {
        User user = new User(request.getUsername());
        return UserResponse.toDto(userRepository.save(user));
    }
    public UserResponse searchUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다"));
        return UserResponse.toDto(user);
    }
}
