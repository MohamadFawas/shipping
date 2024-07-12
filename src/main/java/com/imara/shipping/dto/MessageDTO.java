package com.imara.shipping.dto;
import com.imara.shipping.dto.core.AbstractDTO;
import com.imara.shipping.model.Message;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDTO extends AbstractDTO {

    private long senderId;
    private long receiverId;
    private LocalDateTime timestamp;
    private String content;
    private Message.MessageType type;
    private String fileExtension;
    private String senderName;
    private String receiverName;

}
