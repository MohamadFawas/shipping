package com.imara.shipping.controller;

import com.imara.shipping.controller.core.Result;
import com.imara.shipping.dto.MessageDTO;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    //    @MessageMapping("/chat")
//    @SendTo("/topic/messages")
//    public Result<MessageDTO> greeting(MessageDTO message) throws Exception {
//        return new Result<>(message);
//    }
    @MessageMapping("/chat")
    public void sendMessage(@Payload MessageDTO chatMessage, SimpMessagingTemplate messagingTemplate) {
        messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }


    @MessageMapping("/chat/{driverId}/{customerId}")
    @SendTo("/{driverId}/{customerId}")
    public Result<MessageDTO> greeting(@DestinationVariable("driverId") String driverId, @DestinationVariable("customerId") String customerId, MessageDTO message) throws Exception {
        return new Result<>(message);
    }
}

