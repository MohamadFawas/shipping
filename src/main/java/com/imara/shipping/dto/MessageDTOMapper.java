package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.Message;
import com.imara.shipping.model.User;
import com.imara.shipping.service.UserService;
import com.imara.shipping.utility.TimeZoneConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageDTOMapper extends AbstractDTOMapper<Message,MessageDTO> {
    @Autowired
    private TimeZoneConverter tzConverter;


    @Autowired
    private UserService userService;


    @Override
    public Message getEntity(MessageDTO dto) {
        if (dto == null) return null;
        Message obj = new Message();
        obj.setId(dto.getId());
        obj.setTimestampC(tzConverter.convertFromUTC(dto.getTimestampC()));
        obj.setTimestampU(tzConverter.convertFromUTC(dto.getTimestampU()));
        obj.setCreatedBy(dto.getCreatedBy());
        obj.setUpdatedBy(dto.getUpdatedBy());
        obj.setSenderId(dto.getSenderId());
        obj.setReceiverId(dto.getReceiverId());
        obj.setTimestamp(dto.getTimestamp());
        obj.setContent(dto.getContent());
        obj.setType(dto.getType());
        obj.setFileExtension(dto.getFileExtension());
        return obj;
    }
    @Override
    public MessageDTO getDTO(Message obj, int format) {
        if (obj == null) return null;
        MessageDTO dto = new MessageDTO();
        dto.setId(obj.getId());
        dto.setTimestampC(obj.getTimestampC());
        dto.setTimestampU(obj.getTimestampU());
        dto.setCreatedBy(obj.getCreatedBy());
        dto.setUpdatedBy(obj.getUpdatedBy());
        dto.setSenderId(obj.getSenderId());
        dto.setReceiverId(obj.getReceiverId());
        dto.setContent(obj.getContent());
        dto.setTimestamp(obj.getTimestamp());
        dto.setType(obj.getType());
        dto.setFileExtension(obj.getFileExtension());

        User sender = userService.findByRefId(obj.getSenderId());
        if(sender != null){
            dto.setSenderName(sender.getFullName());
        }

        User receiver = userService.findByRefId(obj.getReceiverId());
        if(receiver != null){
            dto.setReceiverName(receiver.getFullName());
        }

        return dto;
    }
}
