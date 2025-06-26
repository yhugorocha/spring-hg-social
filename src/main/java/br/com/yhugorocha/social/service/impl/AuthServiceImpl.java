package br.com.yhugorocha.social.service.impl;

import br.com.yhugorocha.social.service.AuthService;
import br.com.yhugorocha.social.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final CustumerUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    public String authenticationUser(br.com.yhugorocha.social.dto.LoginRequestDTO requestDTO) {
        String username = requestDTO.username();
        String password = requestDTO.password();

        Authentication authentication = this.authentication(username, password);

        return jwtUtil.createToken(authentication);
    }


    private Authentication authentication(String username, String password) {
        var userDetails = userDetailsService.loadUserByUsername(username);

        if (userDetails == null){
            throw new BadCredentialsException("Invalid username or password");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }
}
