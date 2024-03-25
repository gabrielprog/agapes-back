package br.com.agapesistemas.demo.controllers;

import br.com.agapesistemas.demo.DTO.JwtResponseDTO;
import br.com.agapesistemas.demo.DTO.TokenDTO;
import br.com.agapesistemas.demo.database.entities.UserEntity;
import br.com.agapesistemas.demo.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequestMapping("/user")
public class AuthenticationController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${my.secret.variable}")
    public String secretKey;


    @PostMapping("/authenticate")
    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody UserEntity userEntity){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEntity.getLogin(), userEntity.getPassword()));
        if(authentication.isAuthenticated()){
            return JwtResponseDTO.builder().accessToken(jwtService.GenerateToken(userEntity.getLogin())).build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @PostMapping("/validate-jwt")
    public ResponseEntity<String> validateJWT(@RequestBody TokenDTO token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token.getToken())
                    .getBody();

            Date expiration = claims.getExpiration();
            if (expiration.before(new Date())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token expirado");
            }

            return ResponseEntity.ok("Token válido");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token inválido");
        }
    }


}