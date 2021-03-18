package com.assetbaitur.trello_app_react.rest;

import com.assetbaitur.trello_app_react.dto.UserDTO;
import com.assetbaitur.trello_app_react.entities.CardTasks;
import com.assetbaitur.trello_app_react.entities.Users;
import com.assetbaitur.trello_app_react.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/authentication")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping(value = "/profile")
//    public ResponseEntity<?> profilePage(){
//        Users user = getUser();
//        return new ResponseEntity<>(new UserDTO(user.getId(), user.getFullName(), user.getEmail(), user.getRoles()), HttpStatus.OK);
//    }

    private Users getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            return (Users) authentication.getPrincipal();
        }
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users request_user){

        Users user = new Users();
        user.setEmail(request_user.getEmail());
        user.setPassword(request_user.getPassword());
        user.setFullName(request_user.getFullName());
        if (!userService.saveUser(user)){
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        return ResponseEntity.ok("User registered successfully!");
    }



}
