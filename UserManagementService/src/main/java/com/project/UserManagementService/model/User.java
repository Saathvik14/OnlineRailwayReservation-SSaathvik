package com.project.UserManagementService.model;

import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "User")
@ApiModel(description = "Details about the User Model")
public class User {

    @Transient
    public static final String SEQUENCE_NAME="user_sequence";

    @Id
    private int userId;

    @NotEmpty(message = "required")
    @Pattern(regexp = "^[A-Za-z\\s]+$",message = "Only alphabets and spaces are allowed")
    @Size(min = 3, max = 15,message = "min 3 max 15 alphabets")
    private String userName;

    @NotEmpty(message = "required")
    private String password;

    @NotEmpty(message = "required")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,20}$"
            ,message = "Email should be in the format abc@gmail.com")
    private String email;

    @NotEmpty(message = "required")
    @Pattern(regexp = "^\\d{10}$",message = "Phone number should be of 10 digits")
    private String phone;




}