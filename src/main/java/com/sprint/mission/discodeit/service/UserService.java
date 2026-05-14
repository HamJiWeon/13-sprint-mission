package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.jcf.JCFUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public boolean remove(UUID userId) {
        return userList.removeIf(u -> u.getId().equals(userId));
    }

    private User findUser(UUID userId) {
        return userList.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
    }
}