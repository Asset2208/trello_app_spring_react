package com.assetbaitur.trello_app_react.rest;

import com.assetbaitur.trello_app_react.dto.EditPasswordRequest;
import com.assetbaitur.trello_app_react.dto.JwtRequest;
import com.assetbaitur.trello_app_react.dto.JwtResponse;
import com.assetbaitur.trello_app_react.dto.UserDTO;
import com.assetbaitur.trello_app_react.entities.Users;
import com.assetbaitur.trello_app_react.jwt.JWTTokenGenerator;
import com.assetbaitur.trello_app_react.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class JwtAuthController {

    @Autowired
    private JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/auth")
    public ResponseEntity<?> auth(@RequestBody JwtRequest request) throws Exception{
        authenticate(request.getEmail(), request.getPassword());
        final UserDetails userDetails =
                userService.loadUserByUsername(request.getEmail());

        final String token = jwtTokenGenerator.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    public void authenticate(String email, String password) throws Exception{

        try{

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        }catch (DisabledException e){
            throw new Exception("USER_DISABLED", e);
        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }

    }

    @GetMapping(value = "/profile")
    public ResponseEntity<?> profilePage(){
        Users user = getUser();
        return new ResponseEntity<>(new UserDTO(user.getId(), user.getFullName(), user.getEmail(), user.getRoles()), HttpStatus.OK);
    }

    private Users getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            return (Users) authentication.getPrincipal();
        }
        return null;
    }

    @PutMapping("/editName")
    public ResponseEntity<?> updateUserName(@RequestBody Users req_user){
        Users user = getUser();
        user.setFullName(req_user.getFullName());
        userService.editUser(user);
        return ResponseEntity.ok("Password changed successfully");
    }

    @PutMapping("/editPassword")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> editPassword(@RequestBody EditPasswordRequest editPasswordRequest){
        Users user = getUser();
        System.out.println(editPasswordRequest.getOld_password());
        System.out.println(editPasswordRequest.getNew_password());
        if (user != null){
            if (passwordEncoder.matches(editPasswordRequest.getOld_password(), user.getPassword())){
                user.setPassword(passwordEncoder.encode(editPasswordRequest.getNew_password()));
                userService.editUser(user);
                return ResponseEntity.ok("Password changed successfully");
            }
            return ResponseEntity.badRequest().body("Error: Old password is incorrect!");
        }
        return ResponseEntity.badRequest().body("Error: Unauthorized!");
    }

}
