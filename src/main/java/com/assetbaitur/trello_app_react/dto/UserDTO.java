package com.assetbaitur.trello_app_react.dto;

import com.assetbaitur.trello_app_react.entities.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    private Long id;
    private String email;
    private List<Roles> roles;

}
