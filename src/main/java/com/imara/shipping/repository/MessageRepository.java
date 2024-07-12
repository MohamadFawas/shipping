package com.imara.shipping.repository;

import com.imara.shipping.model.Message;
import com.imara.shipping.repository.core.AbstractRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends AbstractRepository<Message, Long> {


    @Query("SELECT m FROM Message m WHERE (m.senderId = :userId OR m.receiverId = :userId) ORDER BY m.timestamp DESC")
    List<Message> findRecentMessagesForUser(long userId);


    @Query("SELECT m FROM Message m WHERE (m.senderId = :userId1 AND m.receiverId = :userId2 OR m.senderId = :userId2 AND m.receiverId = :userId1) ORDER BY m.timestamp ASC")
    List<Message> findRecentMessagesBetweenUsers(long userId1, long userId2);



}
