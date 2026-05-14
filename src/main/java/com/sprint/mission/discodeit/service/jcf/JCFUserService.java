package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.User;

import java.util.List;
import java.util.UUID;

public interface JCFUserService {

    void save(User user);

    void updateNickname(UUID userId, String nickname);

    void updateEmail(UUID userId, String email);

    void updatePassword(UUID userId, String password);

    List<User> findAll();

    boolean remove(UUID userId);
}
