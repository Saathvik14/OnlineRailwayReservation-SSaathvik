package com.project.UserManagementService.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class AuthenticationRequest {


    private String userName;



    private String password;


}
