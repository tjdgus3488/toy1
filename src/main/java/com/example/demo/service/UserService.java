package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserSignUpDto;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserDto joinUser(UserSignUpDto userSignUpDto) {
        if (userRepository.findByEmail(userSignUpDto.getEmail()).isPresent()) {
            throw new RuntimeException("중복된 이메일입니다");
            // 예외 처리 고려
        }

        User newUser = new User();
        newUser.setEmail(userSignUpDto.getEmail());
        newUser.setNickname(userSignUpDto.getNickname());
        newUser.setPassword(encoder.encode(userSignUpDto.getPassword()));
        userRepository.save(newUser);

        UserDto savedUser = new UserDto();
        savedUser.setEmail(newUser.getEmail());
        savedUser.setNickname(newUser.getNickname());

        return savedUser;
    }

    public UserDto loginUser(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다"));
        // 예외 처리 고려

        if (!encoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 잘못 입력되었습니다");
            // 예외 처리 고려
        }

        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setNickname(user.getNickname());

        return userDto;
    }
}