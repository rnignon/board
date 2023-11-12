package goorm.board.controller;

import goorm.board.dto.request.UserRequest;
import goorm.board.dto.response.UserResponse;
import goorm.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserResponse createUser(
            @RequestBody UserRequest request
            ) {
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    public UserResponse searchUser(
            @PathVariable Long id
            ) {
        return userService.searchUser(id);
    }
}
