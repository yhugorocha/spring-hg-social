package br.com.yhugorocha.social.controller;

import br.com.yhugorocha.social.dto.LoginRequestDTO;
import br.com.yhugorocha.social.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Void> authenticationUser(@Valid @RequestBody LoginRequestDTO requestDTO){
        var token = authService.authenticationUser(requestDTO);

        ResponseCookie cookie = ResponseCookie.from("backendHgSocial", token)
                .httpOnly(true)
                .path("/")
                .secure(false)
                .sameSite("Strict")
                .build();

        return ResponseEntity.ok().header("Set-Cookie", cookie.toString()).build();
    }
}
