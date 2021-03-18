package com.assetbaitur.trello_app_react.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditPasswordRequest implements Serializable{

    private String old_password;
    private String new_password;

}
