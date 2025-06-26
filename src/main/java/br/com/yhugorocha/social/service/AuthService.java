package br.com.yhugorocha.social.service;

import br.com.yhugorocha.social.dto.LoginRequestDTO;

public interface AuthService {

    String authenticationUser(LoginRequestDTO requestDTO);
}
