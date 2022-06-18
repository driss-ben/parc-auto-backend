package com.gestion_parc.demo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;



@AllArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    PasswordEncoderConfig passwordEncoderConfig;

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    UserService userService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        AppUser user = null;
        try {

            user = new ObjectMapper().readValue(req.getInputStream(),AppUser.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                                                user.getUsername(),
                                                user.getPassword()
                                            );

            Authentication authenticate = authenticationManager.authenticate(authentication);

            return authenticate;

        }catch (Exception e){
            res.addHeader("userNotFound","Utilisateur n'exsite pas");
            return null;
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String key = "NAVABUSINESS_NAVABUSINESS_NAVABUSINESS_NAVABUSINESS_NAVABUSINESS_NAVABUSINESS";

        try {
            String token = Jwts.builder()
                    .setSubject(authResult.getName())
                    .setIssuedAt(new Date())
                    .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                    .claim("authorities", authResult.getAuthorities())
                    .signWith(SignatureAlgorithm.HS512,key)
                    .compact();
            res.addHeader("Authentication","Bearer "+token);
        }catch (Exception e){
            res.addHeader("exception" ,"heree");
        }


    }



}
