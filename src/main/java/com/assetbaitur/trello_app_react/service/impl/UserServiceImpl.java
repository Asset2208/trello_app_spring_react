package com.assetbaitur.trello_app_react.service.impl;

import com.assetbaitur.trello_app_react.entities.Roles;
import com.assetbaitur.trello_app_react.entities.Users;
import com.assetbaitur.trello_app_react.repo.RoleRepository;
import com.assetbaitur.trello_app_react.repo.UserRepository;
import com.assetbaitur.trello_app_react.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(s);
        if(user!=null){
            return user;
        }else{
            throw new UsernameNotFoundException("USER NOT FOUND");
        }
    }

    @Override
    public Users editUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public boolean saveUser(Users user) {
        Users userFromDb = userRepository.findByEmail(user.getEmail());
        if (userFromDb != null){
            return false;
        }

        List<Roles> roles = new ArrayList<>();
        roles.add(roleRepository.getOne(1L));
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
}
