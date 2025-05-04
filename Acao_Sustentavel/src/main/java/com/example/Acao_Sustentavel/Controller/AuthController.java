package com.example.Acao_Sustentavel.Controller;


import com.example.Acao_Sustentavel.config.JwtUtil;
import com.example.Acao_Sustentavel.model.dto.AuthRequest;
import com.example.Acao_Sustentavel.model.dto.AuthResponse;
import com.example.Acao_Sustentavel.model.entity.AcaoSustentavel;
import com.example.Acao_Sustentavel.service.AcaoSustentavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.username());
            if (passwordEncoder.matches(authRequest.password(), userDetails.getPassword()))
            {
                String token = jwtUtil.generateToken(String.valueOf(new User(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities())));
                return ResponseEntity.ok(new AuthResponse(token, userDetails.getUsername()));
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
            }
        }catch(UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
        }
    }
}