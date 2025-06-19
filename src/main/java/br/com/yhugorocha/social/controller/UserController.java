package br.com.yhugorocha.social.controller;

import br.com.yhugorocha.social.dto.UserRequestDTO;
import br.com.yhugorocha.social.entities.User;
import br.com.yhugorocha.social.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public User register(UserRequestDTO user) {
        return userService.register(user);
    }
}
