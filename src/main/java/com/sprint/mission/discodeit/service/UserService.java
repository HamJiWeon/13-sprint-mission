package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.jcf.JCFUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserService implements JCFUserService {

    private final List<User> userList = new ArrayList<>();

    @Override
    public void save(User user) {
        userList.add(user);
    }

    @Override
    public void updateNickname(UUID userId, String nickname) {
        User user = findUser(userId);
        user.updateNickname(nickname);
    }

    @Override
    public void updateEmail(UUID userId, String email) {
        User user = findUser(userId);
        user.updateEmail(email);
    }

    @Override
    public void updatePassword(UUID userId, String password) {
        User user = findUser(userId);
        user.updatePassword(password);
    }

    @Override
    public void remove(UUID userId) {
        boolean flag = userList.removeIf(u -> u.getId().equals(userId));
        if (flag) System.out.println("삭제되었습니다.");
        else System.out.println("삭제 실패했습니다.");
    }

    @Override
    public void findAll() {
        userList.stream()
                .map(this::formatter)
                .forEach(System.out::println);
    }

    @Override
    public void findById(UUID userId) {
        User user = findUser(userId);
        System.out.println(formatter(user));
    }

    private User findUser(UUID userId) {
        return userList.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
    }

    private String formatter(User user) {
        return "User{" +
                "id=" + user.getId() +
                ", username='" + user.getUsername() + '\'' +
                ", nickname='" + user.getNickname() + '\'' +
                ", email='" + user.getEmail() + '\'' +
                ", password='" + user.getPassword() + '\'' +
                ", createdAt=" + user.getCreatedAt() +
                ", updatedAt=" + user.getUpdatedAt() +
                '}';
    }
}