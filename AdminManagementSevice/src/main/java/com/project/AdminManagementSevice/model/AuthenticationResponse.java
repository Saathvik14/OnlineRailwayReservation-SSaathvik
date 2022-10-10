package com.project.AdminManagementSevice.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class AuthenticationResponse {
    private String response;



    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


}
