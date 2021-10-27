package com.techwave.cartRelation.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private String fullName;
    private String username;
    private String mobile;
    private String email;
    private String password;

}
