package com.project.AdminManagementSevice.controller;


import com.project.AdminManagementSevice.customexception.AdminAlreadyExistException;
import com.project.AdminManagementSevice.model.Admin;
import com.project.AdminManagementSevice.model.AuthenticationRequest;
import com.project.AdminManagementSevice.model.AuthenticationResponse;
import com.project.AdminManagementSevice.service.AdminService;
import com.project.AdminManagementSevice.utils.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin("*")
@RestController
@RequestMapping("/admin/auth")
public class AuthController {

    @Autowired
    public  AdminService adminservice;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

/*

 @PostMapping("/signup")
    public ResponseEntity RegisterAdmin(@Valid @RequestBody Admin admin) throws AdminAlreadyExistException{
            Admin savedAdmin=adminservice.add(admin);
            String userName=savedAdmin.getUserName();
            String password=savedAdmin.getPassword();
            return ResponseEntity.ok(new AuthenticationResponse("Successfully signed up with username"));
    }
 */

    @PostMapping("/signup")
    @ApiOperation(value = "Registers Admin",notes = "Provide the admin details to register",response = Admin.class)
    private ResponseEntity registerAdmin(@Valid @RequestBody Admin admin)throws AdminAlreadyExistException {
        try{
            Admin savedUser=adminservice.add(admin);
            //return new ResponseEntity<>(savedAdmin, HttpStatus.OK);
            String userName=savedUser.getUserName();
            String password=savedUser.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));
            UserDetails admin1=adminservice.loadUserByUsername(userName);

            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,jwtUtils.generateToken(admin1)).body(admin1);
        }
        catch (Exception exception)
        {
            return new  ResponseEntity( "Error occured while signing up",HttpStatus.CONFLICT);
        }
    }





    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody AuthenticationRequest authenticationRequest) {
        String username = authenticationRequest.getUserName();
        String password = authenticationRequest.getPassword();
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            //UserDetails admin = (UserDetails) authentication.getPrincipal();
            UserDetails admin = adminservice.loadUserByUsername(username);
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtUtils.generateToken(admin)).body(admin);
        } catch (Exception exception) {
            return new ResponseEntity("Error during authentication", HttpStatus.CONFLICT);
        }
    }

//        UserDetails loadedUser = adminService.loadUserByUsername(username);
//        String generatedToken = jwtUtils.generateToken(loadedUser);
//        return ResponseEntity.ok(new AuthenticationResponse(generatedToken));






    @GetMapping("/test")
    public ResponseEntity<?>  jwtValidation(@RequestParam String token, @AuthenticationPrincipal UserDetails admin) {

            Boolean result=jwtUtils.validateToken(token,admin);
            return ResponseEntity.ok(result);

    }



}
