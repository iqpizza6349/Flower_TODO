package com.github.iqpizza6349.flower_tood.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class UserDTO {

    private String email;
    private String password;
    private String auth;

}
