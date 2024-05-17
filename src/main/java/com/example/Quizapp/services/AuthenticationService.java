package com.example.Quizapp.services;

import com.example.Quizapp.dto.JwtAuthenticationResponse;
import com.example.Quizapp.dto.RefreshTokenRequest;
import com.example.Quizapp.dto.SignInRequest;
import com.example.Quizapp.dto.SignUpRequest;
import com.example.Quizapp.entities.User;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signin(SignInRequest signinRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
