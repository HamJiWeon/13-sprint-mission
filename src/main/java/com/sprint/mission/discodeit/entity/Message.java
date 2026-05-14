package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class Message {

    private final UUID id;

    private String content;

    private final Long createdAt;

    private Long updatedAt;

    private final UUID userId;

    private final UUID channelId;

    public Message(String content, UUID userId, UUID channelId) {
        validateContent(content);
        validateId(userId);
        validateId(channelId);

        this.id = UUID.randomUUID();
        this.content = content;
        this.userId = userId;
        this.channelId = channelId;
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
    }

    public UUID getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public UUID getChannelId() {
        return channelId;
    }

    public UUID getUserId() {
        return userId;
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

    private static void validateContent(String content) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("메시지는 비어있을 수 없습니다.");
        }
    }

    private static void validateId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("채널 또는 사람을 인식할 수 없습니다.");
        }
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", userId=" + userId +
                ", channelId=" + channelId +
                '}';
    }
}
