package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class Message {

    private final UUID id;

    private String content;

    private final Long createdAt;

    private Long updatedAt;

    public Message(String content) {
        this.id = UUID.randomUUID();
        this.content = content;
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
    }

    public UUID getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void updateContent(String content) {
        this.content = content;
        updatedAt = System.currentTimeMillis();
    }
}
