package com.ssafy.board.security;

public enum UserRole {
    ADMIN("관리자"),
    GYMOWNER("주인"),
    TRAINER("트레이너"),
    USER("일반사용자");

    private String label;

    private UserRole(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}