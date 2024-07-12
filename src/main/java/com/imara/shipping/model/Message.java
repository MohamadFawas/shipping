package com.imara.shipping.model;
import com.imara.shipping.model.core.AbstractObject;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "messages")
public class Message extends AbstractObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long senderId;

    @Column(nullable = false)
    private long receiverId;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private MessageType type;

    private String fileExtension;

    private LocalDateTime timestamp;



    public enum MessageType {
        TEXT,
        AUDIO,
        IMAGE
    }
}
