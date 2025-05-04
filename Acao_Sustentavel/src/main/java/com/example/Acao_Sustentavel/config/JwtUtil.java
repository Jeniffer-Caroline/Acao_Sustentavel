package com.example.Acao_Sustentavel.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

@Configuration
public class JwtUtil {
    // Implementação para obter as autoridades do usuário a partir do token
    public List<GrantedAuthority> getAuthorities(String token) {

        return List.of();
    }
    //Chave secreta para gerar e validar tokens
    private static final String SECRET = "";
// Tempo de expiração do token em minutos
    private static final long EXPIRATION_TIME = 30;
//Método para gerar token JWT
    public static String generateToken(String username) {
        //Cria um objeto de claims
        Claims claims = Jwts.claims().setSubject(username);
//Gera o teken JWT
        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME * 60 * 1000))
                .compact();

        return token;

    }
    //Método para validar token JWT
    public static boolean validateToken(String token) {
        try {
            //verifica se o token é válido
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (JwtException e){
            return false;
        }
    }
    //Método para extrair claims
    public static String extractUsername(String token) {
        //Extrai os claims do token
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        //retorna username
        return claims.getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        return false;
    }
}
