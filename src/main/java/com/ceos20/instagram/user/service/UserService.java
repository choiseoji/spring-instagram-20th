package com.ceos20.instagram.user.service;

import com.ceos20.instagram.user.domain.User;
import com.ceos20.instagram.user.dto.SaveUserRequest;
import com.ceos20.instagram.user.dto.UpdateUserInfoRequest;
import com.ceos20.instagram.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // user 저장
    public void saveUser(SaveUserRequest saveUserRequest) {

        User user = User.builder()
                .username(saveUserRequest.getUsername())
                .nickname(saveUserRequest.getNickname())
                .password(saveUserRequest.getPassword())
                .email(saveUserRequest.getEmail())
                .build();
        userRepository.save(user);
    }

    // user 정보 수정
    public void updateUserInfo(UpdateUserInfoRequest updateUserInfoRequest, User user) {

        user.updateInfo(
                updateUserInfoRequest.getUsername(),
                updateUserInfoRequest.getNickname(),
                updateUserInfoRequest.getPassword(),
                updateUserInfoRequest.getEmail(),
                updateUserInfoRequest.getImageUrl()
                );
        userRepository.save(user);
    }
}
