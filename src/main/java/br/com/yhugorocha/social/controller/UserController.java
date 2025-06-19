package br.com.yhugorocha.social.controller;

import br.com.yhugorocha.social.dto.UserRequestDTO;
import br.com.yhugorocha.social.dto.UserResponseDTO;
import br.com.yhugorocha.social.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable String email) {
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }

    @PostMapping("/{userId}/follow/{followUserId}")
    public ResponseEntity<UserResponseDTO> followUser(@PathVariable Long userId, @PathVariable Long followUserId) {
        return ResponseEntity.ok(userService.followUser(userId, followUserId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDTO>> searchUser(@RequestParam String query) {
        return ResponseEntity.ok(userService.searchUser(query));
    }
}
