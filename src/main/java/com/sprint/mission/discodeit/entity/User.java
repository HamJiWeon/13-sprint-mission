package com.sprint.mission.discodeit.entity;

import com.sprint.mission.discodeit.validate.EmailValidator;

import java.util.UUID;

public class User {

    private final UUID id;

    private final String username;

    private String nickname;

    private String email;

    private String password;

    private final Long createdAt;

    private Long updatedAt;

    public User(String username, String nickname, String email, String password) {
        validateUsername(username);
        validateNickname(nickname);
        validateEmail(email);

        this.id = UUID.randomUUID();
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
    }

    // test
    public static void main(String[] args) throws InterruptedException {
        User user = new User("함지원", "HamJi", "qwerty12@gmail.com", "qwerty12");

        System.out.println(user.getId());
        System.out.println(user.getNickname());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getCreatedAt());

        System.out.println(user.getUpdatedAt());

        Thread.sleep(1000);

        user.updateNickname("HamJi12");
    }
    public UUID getId() {
        return id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public void updateNickname(String nickname) {
        validateNickname(nickname);
        this.nickname = nickname;
        update();
    }

    public String getEmail() {
        return email;
    }

    public void updateEmail(String email) {
        if (email == null || !EmailValidator.validate(email)) {
            throw new IllegalArgumentException("올바른 이메일 형식이 아닙니다.");
        }
        this.email = email;
        update();
    }

    public String getPassword() {
        return password;
    }

    public void updatePassword(String password) {
        this.password = password;
        update();
    }

    public void update() {
        this.updatedAt = System.currentTimeMillis();
    }

    private static void validateEmail(String email) {
        if (email == null || !EmailValidator.validate(email)) throw new IllegalArgumentException("올바른 이메일 형식이 아닙니다.");
    }

    private static void validateNickname(String nickname) {
        if (nickname == null || nickname.isBlank()) throw new IllegalArgumentException("잘못된 닉네임 형식입니다.");
    }

    private static void validateUsername(String username) {
        if (username == null || username.isBlank()) throw new IllegalArgumentException("이름을 작성하세요.");
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}