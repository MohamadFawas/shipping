package com.imara.shipping.service;

import com.imara.shipping.config.AppConstants;
import com.imara.shipping.controller.core.Result;
import com.imara.shipping.dto.MessageDTO;
import com.imara.shipping.model.Message;
import com.imara.shipping.repository.MessageRepository;
import com.imara.shipping.repository.UserRepository;
import com.imara.shipping.repository.core.AbstractRepository;
import com.imara.shipping.service.core.AbstractService;
import com.imara.shipping.utility.Base64ToFileConverter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class MessageService extends AbstractService<Message> {


    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    protected AbstractRepository getRepository() {
        return null;
    }

    @Override
    protected Logger getLog() {
        return null;
    }



    public Result<Message> sendMessage(MessageDTO messageRequest) {
        Message newMessage = new Message();

        newMessage.setSenderId(messageRequest.getSenderId());
        newMessage.setReceiverId(messageRequest.getReceiverId());
        newMessage.setTimestamp(LocalDateTime.now());
        newMessage.setType(messageRequest.getType());
        newMessage.setFileExtension(messageRequest.getFileExtension());


        if(messageRequest.getType() == Message.MessageType.TEXT){

            newMessage.setContent(messageRequest.getContent());

        } else if (messageRequest.getType() == Message.MessageType.IMAGE) {

            String imageUrl = Base64ToFileConverter.saveImage(
                    messageRequest.getContent(),messageRequest.getFileExtension(), AppConstants.IMAGE_FILE_PREFIX);

            newMessage.setContent(imageUrl);
        }

        else if (messageRequest.getType() == Message.MessageType.AUDIO) {

            String audioUrl = Base64ToFileConverter.saveAudio(
                    messageRequest.getContent(),messageRequest.getFileExtension(), AppConstants.AUDIO_FILE_PREFIX);

            newMessage.setContent(audioUrl);
        }

        messageRepository.save(newMessage);

        return new Result<>(newMessage);

    }

    public List<Message> getMessagesBetweenUsers(long userId1, long userId2) {

        return messageRepository.findRecentMessagesBetweenUsers(userId1, userId2);
    }


    public List<Message> getRecentContactsForUser(long userId) {
        List<Message> messages = messageRepository.findRecentMessagesForUser(userId);
        List<Message> filteredMessages = new ArrayList<>();

        Set<String> senderReceiverPairs = new HashSet<>();

        for (Message message : messages) {
            String pair = message.getSenderId() + "-" + message.getReceiverId();
            String pairReverse = message.getReceiverId() + "-" + message.getSenderId();
            if (!senderReceiverPairs.contains(pair) && !senderReceiverPairs.contains(pairReverse)) {
                filteredMessages.add(message);
                senderReceiverPairs.add(pair);
            }
        }

        return filteredMessages;
    }


}
