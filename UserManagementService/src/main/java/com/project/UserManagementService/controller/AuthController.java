package com.project.UserManagementService.controller;



import com.project.UserManagementService.model.AuthenticationRequest;
import com.project.UserManagementService.model.AuthenticationResponse;
import com.project.UserManagementService.model.User;
import com.project.UserManagementService.service.UserService;
import com.project.UserManagementService.utils.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/user/auth")
public class AuthController {

    @Autowired
    public UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;




    @CrossOrigin("*")
    @PostMapping("/signup")
    @ApiOperation(value = "Registers User",notes = "Provide the user details to register",response = User.class)
    public ResponseEntity registerAdmin(@Valid @RequestBody User user) {
        try{
            User savedUser=userService.add(user);
            //return new ResponseEntity<>(savedAdmin, HttpStatus.OK);
            String userName=savedUser.getUserName();



            return ResponseEntity.ok(new AuthenticationResponse("Successfully signed up with username"+userName));
        }
        catch (Exception exception)
        {
            return new  ResponseEntity( "Error occured while signing up",HttpStatus.CONFLICT);
        }
    }




    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody AuthenticationRequest authenticationRequest){
        String username = authenticationRequest.getUserName();
        String password = authenticationRequest.getPassword();
        try{
            Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
            //UserDetails admin = (UserDetails) authentication.getPrincipal();
            UserDetails user = userService.loadUserByUsername(username);
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,jwtUtils.generateToken(user)).body(user);
        }
        catch (Exception exception){
            return new ResponseEntity("Error during authentication", HttpStatus.CONFLICT);
        }
//        UserDetails loadedUser = adminService.loadUserByUsername(username);
//        String generatedToken = jwtUtils.generateToken(loadedUser);
//        return ResponseEntity.ok(new AuthenticationResponse(generatedToken));
    }

    @GetMapping("/validate")
    public ResponseEntity<?>  jwtValidation(@RequestParam String token, @AuthenticationPrincipal UserDetails user) {

            Boolean result=jwtUtils.validateToken(token,user);
            return ResponseEntity.ok(result);

    }



}
