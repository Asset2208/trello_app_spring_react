package com.assetbaitur.trello_app_react.service;

import com.assetbaitur.trello_app_react.entities.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean saveUser(Users user);
    Users editUser(Users user);
}
