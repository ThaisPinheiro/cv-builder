package com.cvbuilder.usersLogin;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvbuilder.usersLogin.dtos.AuthenticationDto;
import com.cvbuilder.usersLogin.dtos.LoginRegisterDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class LoginAuthenticationController {
    
    final AuthenticationManager authenticationManager; 
    final LoginRepository loginRepository;

    public LoginAuthenticationController(AuthenticationManager authenticationManager, LoginRepository loginRepository) {
        this.authenticationManager = authenticationManager;
        this.loginRepository = loginRepository;
    }
    
    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody @Valid AuthenticationDto authenticationDto) {
        UsernamePasswordAuthenticationToken userNamePassword = 
                    new UsernamePasswordAuthenticationToken(authenticationDto.login(), authenticationDto.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid LoginRegisterDto registerDto) {
        if(this.loginRepository.findByLogin(registerDto.login()) != null) {
            return ResponseEntity.badRequest().build();
        } 

        String encryptedPassword =new BCryptPasswordEncoder().encode(registerDto.password());
        LoginModel loginUser = new LoginModel(registerDto.login(), encryptedPassword, registerDto.role());
        this.loginRepository.save((loginUser));
        return ResponseEntity.ok().build();
    }
}
