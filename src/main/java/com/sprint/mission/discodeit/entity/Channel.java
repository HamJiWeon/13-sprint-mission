package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class Channel {

    private final UUID id;

    private String title;

    private String description;

    private ChannelType type;

    private final Long createdAt;

    private Long updatedAt;

    public Channel(String title, String description) {
        this.id = UUID.randomUUID();

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("방의 제목을 작성해주세요.");
        } else this.title = title;

        this.description = description;
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ChannelType getType() {
        return type;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updateType(ChannelType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
