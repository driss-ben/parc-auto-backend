package com.gestion_parc.demo.security;


import com.gestion_parc.demo.security.service.AppRoleRepository;
import com.gestion_parc.demo.security.service.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    PasswordEncoderConfig passwordEncoderConfig;
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    AppRoleRepository appRoleRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AppUser appUser = appUserRepository.findByEmail(email);
        User user = null;
        if (appUser != null){
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                for (AppRole role: appUser.getRoles()) {
                    authorities.add(new SimpleGrantedAuthority(role.getName()));
                }
                user = new User(
                        appUser.getUsername(),
                        passwordEncoderConfig.passwordEncoder().encode(appUser.getPassword()),
                        authorities);
            }else {
                throw new IllegalStateException("exception");
        }
        return user;
    }

    public void add(AppUser user){
        AppUser appUser = appUserRepository.findByEmail(user.getEmail());
        if (appUser!=null){
            appUserRepository.save(user);
        }else {
            throw new IllegalStateException("il exist deja un utilisateur avec cet email");
        }
    }

    public void addAdmin() {
        List<AppUser> users = appUserRepository.findAll();
        if (users.size() == 0) {
            AppUser admin = new AppUser();
            admin.setEmail("admin@gmail.com");
            admin.setPassword("password-admin");
            admin.setUsername("admin");

            AppRole role1 = new AppRole(1l, "ADMIN");
            AppRole role2 = new AppRole(2l, "CONDUCTEUR");
            AppRole role3 = new AppRole(3l, "APPROVISIONNEMENT");
            AppRole role4 = new AppRole(4l, "MECANICIEN");

            appRoleRepository.save(role1);
            appRoleRepository.save(role2);
            appRoleRepository.save(role3);
            appRoleRepository.save(role4);

            AppRole role = appRoleRepository.findByName("ADMIN");

            admin.setRoles(List.of(role));

            appUserRepository.save(admin);


        }
    }
}
