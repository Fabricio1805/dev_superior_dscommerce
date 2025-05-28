package com.devsuperior.dscommerce.infrastructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dscommerce.domain.entity.User;
import com.devsuperior.dscommerce.infrastructure.exception.ForbiddenException;

@Service
public class AuthService {

    @Autowired
    private UserService userService;


    public void validSelfOrAdmin(final Long userId){
        User user = userService.authenticated();
        if (!user.hasRole("ROLE_ADMIN") && !user.getId().equals(userId)) {
            throw new ForbiddenException("Access denied");
        }
    } 
}
