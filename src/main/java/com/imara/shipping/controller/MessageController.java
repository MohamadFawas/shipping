package com.imara.shipping.controller;

import com.imara.shipping.controller.core.AbstractController;
import com.imara.shipping.controller.core.Result;
import com.imara.shipping.dto.MessageDTO;
import com.imara.shipping.dto.MessageDTOMapper;
import com.imara.shipping.model.Message;
import com.imara.shipping.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages/")
@Slf4j
@Transactional
public class
MessageController extends AbstractController<Message, MessageDTO> {
    @Autowired
    MessageService messageService;
    @Autowired
    MessageDTOMapper messageDTOMapper;

    @Override
    protected MessageService getService() {
        return messageService;
    }

    @Override
    protected Logger getLog() {
        return log;
    }

    protected MessageDTOMapper getDTOMapper() {
        return messageDTOMapper;
    }

//    @PostMapping("save")
//    public Result<MessageDTO> save(@RequestBody MessageDTO messageDTO) {
//        return super.save(messageDTO);
//    }

    @GetMapping("get_all")
    public Result<List<MessageDTO>> getAll() {
        return super.getAll();
    }

    @GetMapping("get_by_id")
    public Result<MessageDTO> getByID(@RequestParam("id") long id) {
        return super.getByID(id);
    }

    @PostMapping("delete")
    public Result deleteById(@RequestParam("id") long id) {
        return super.deleteById(id);
    }

    @PostMapping("send")
    public Result<Message> sendMessage(@RequestBody MessageDTO messageDTO) {
        return messageService.sendMessage(messageDTO);
    }

    @GetMapping("conversation/{userId1}/{userId2}")
    public Result<List<MessageDTO>> getMessagesBetweenUsers(
            @PathVariable("userId1") long userId1,
            @PathVariable("userId2") long userId2
    ) {
        List<MessageDTO> messages = messageDTOMapper.getDTOList(messageService.getMessagesBetweenUsers(userId1, userId2));
        return new Result<>(messages);
    }

    @GetMapping("recent_contacts/{userId}")
    public Result<List<MessageDTO>> findRecentMessagesForUser(@PathVariable("userId") long userId) {
        List<Message> recentMessages = messageService.getRecentContactsForUser(userId);
        List<MessageDTO> recentContacts = messageDTOMapper.getDTOList(recentMessages);
        return new Result<>(recentContacts);
    }

    @MessageMapping("/message")
    @SendTo("/queue/reply")
    public MessageDTO greeting(MessageDTO message) throws Exception {
        super.save(message);
        Thread.sleep(1000); // simulated delay
        return message;
    }

    @GetMapping("greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
