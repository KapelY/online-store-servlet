package com.study.service;

import com.study.domain.User;

import java.time.LocalDateTime;

public record Session(LocalDateTime expiryTime, User user) {
    public boolean isValid() {
        return expiryTime.isBefore(LocalDateTime.now());
    }
}
