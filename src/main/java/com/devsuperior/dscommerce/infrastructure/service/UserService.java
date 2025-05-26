package com.devsuperior.dscommerce.infrastructure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscommerce.application.dto.UserDTO;
import com.devsuperior.dscommerce.domain.entity.Role;
import com.devsuperior.dscommerce.domain.entity.User;
import com.devsuperior.dscommerce.infrastructure.projections.UserDetailsProjection;
import com.devsuperior.dscommerce.infrastructure.repository.UserRepository;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> results = userRepository.searchUserAndRolesByEmail(username);

        if (results.isEmpty()) {
            throw new UsernameNotFoundException("Email not found");
        }

        User user = new User();
        user.setEmail(username);
        user.setPassword(results.get(0).getPassword());

        for (UserDetailsProjection result : results) {
            Role role = new Role(result.getRoleId(), result.getAuthority());

            user.addRole(role);
        }

        return user;

    }

    protected User authenticated() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");

            User user = userRepository.findByEmail(username).get();

            return user;
        } catch (Exception e) {
            throw new UsernameNotFoundException("Email not found");
        }
    }

    @Transactional(readOnly = true)
    public UserDTO getMe() {
        UserDTO userDTO = new UserDTO(authenticated());

        return userDTO;
    }

}
