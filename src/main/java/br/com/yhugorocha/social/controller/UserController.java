package br.com.yhugorocha.social.controller;

import br.com.yhugorocha.social.dto.UserRequestDTO;
import br.com.yhugorocha.social.dto.UserResponseDTO;
import br.com.yhugorocha.social.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(UserRequestDTO user) {
        return ResponseEntity.ok(userService.register(user));
    }
}
