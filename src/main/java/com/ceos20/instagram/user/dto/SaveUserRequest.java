package com.ceos20.instagram.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SaveUserRequest {

    private String username;

    private String nickname;

    private String password;

    private String email;
}
