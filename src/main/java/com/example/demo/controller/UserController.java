package com.example.demo.controller;


import com.example.demo.dto.LoginDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserSignUpDto;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    @PostMapping("/join")
    public ResponseEntity<UserDto> joinUser(@RequestBody UserSignUpDto userSignUpDto) {
        return new ResponseEntity<>(userService.joinUser(userSignUpDto), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(userService.loginUser(loginDto), HttpStatus.OK);
    }
}