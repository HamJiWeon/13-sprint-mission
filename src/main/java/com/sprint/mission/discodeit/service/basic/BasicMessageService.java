package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.request.MessageCreateRequest;
import com.sprint.mission.discodeit.dto.request.MessageUpdateRequest;
import com.sprint.mission.discodeit.dto.response.MessageResponse;
import com.sprint.mission.discodeit.entity.BinaryContent;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.BinaryContentRepository;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BasicMessageService implements MessageService {

    private final MessageRepository messageRepository;
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final BinaryContentRepository contentRepository;

    @Override
    public MessageResponse create(MessageCreateRequest request) {
        if (!channelRepository.existsById(request.channelId())) {
            throw new NoSuchElementException("채널을 찾을 수 없습니다. ID: " + request.channelId());
        }
        if (!userRepository.existsById(request.authorId())) {
            throw new NoSuchElementException("저자를 찾을 수 없습니다. ID: " + request.authorId());
        }

        List<UUID> attachments = request.attachments() == null ? List.of()
                : request.attachments().stream()
                .map(attach -> new BinaryContent(
                        attach.fileName(),
                        attach.size(),
                        attach.contentType(),
                        attach.bytes()))
                .map(contentRepository::save)
                .map(BinaryContent::getId)
                .toList();

        Message message = new Message(
                request.content(),
                request.channelId(),
                request.authorId(),
                attachments
        );

        return toResponse(messageRepository.save(message));
    }

    @Override
    public MessageResponse find(UUID messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new NoSuchElementException("메시지 ID: " + messageId + " 를 찾을 수 없습니다."));

        return toResponse(message);
    }

    @Override
    public List<MessageResponse> findAllByChannelId(UUID channelId) {
        if (!channelRepository.existsById(channelId)) {
            throw new NoSuchElementException("채널 ID: " + channelId + " 를 찾을 수 없습니다.");
        }

        return messageRepository.findAll().stream()
                .filter(message -> message.getChannelId().equals(channelId))
                .map(this::toResponse)
                .toList();
    }

    @Override
    public MessageResponse update(MessageUpdateRequest request) {
        Message message = messageRepository.findById(request.messageId())
                .orElseThrow(() -> new NoSuchElementException("메시지 ID: " + request.messageId() + " 를 찾을 수 없습니다."));

        message.update(request.newContent());

        return toResponse(messageRepository.save(message));
    }

    @Override
    public void delete(UUID messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new NoSuchElementException("메시지 ID: " + messageId + " 를 찾을 수 없습니다."));

        message.getAttachmentIds().forEach(contentRepository::deleteById);

        messageRepository.deleteById(messageId);
    }

    private MessageResponse toResponse(Message message) {
        return new MessageResponse(
                message.getId(),
                message.getCreatedAt(),
                message.getUpdatedAt(),
                message.getContent(),
                message.getChannelId(),
                message.getAuthorId(),
                message.getAttachmentIds()
        );
    }
}
