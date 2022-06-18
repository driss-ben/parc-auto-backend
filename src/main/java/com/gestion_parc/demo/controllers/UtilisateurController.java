package com.gestion_parc.demo.controllers;

import com.gestion_parc.demo.security.AppUser;
import com.gestion_parc.demo.security.JwtAuthenticationFilter;
import com.gestion_parc.demo.security.UserService;
import com.gestion_parc.demo.security.service.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin( origins = "http://localhost:3000", allowedHeaders = "*")
@RequestMapping("/api/")
public class UtilisateurController {
    @Autowired
    UserService userService;

    @Autowired
    AppUserRepository appUserRepository;

    @PostMapping
    public Object add(@RequestBody AppUser user){
        return null;
    }

    @GetMapping("user/{email}")
    public Object getUser(@PathVariable String email){
        try {
            return appUserRepository.findByEmail(email);
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PostMapping("/add-admin")
    public void addAdmin(){
        userService.addAdmin();
    }
}
