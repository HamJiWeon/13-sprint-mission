package com.sprint.mission.discodeit.entity;

public enum ChannelType {
    PUBLIC("공개"),
    PRIVATE("비공개");

    private final String privacy;

    ChannelType(String privacy) {
        this.privacy = privacy;
    }

    public String getPrivacy() {
        return privacy;
    }
}
