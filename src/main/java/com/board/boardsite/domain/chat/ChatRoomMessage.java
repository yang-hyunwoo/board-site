package com.board.boardsite.domain.chat;

import com.board.boardsite.domain.AuditingFields;
import com.board.boardsite.domain.user.TripUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Entity
public class ChatRoomMessage extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(optional = false)
    private ChatRoom chatRoom;

    @Setter
    @ManyToOne(optional = false)
    private TripUser tripUser; // 유저 정보 (ID)

    @Setter
    @Column(nullable = false, length = 4000)
    private String content;

    protected ChatRoomMessage() {}

    private ChatRoomMessage(ChatRoom chatRoom ,
                            TripUser tripUser,
                            String content){
        this.chatRoom = chatRoom;
        this.tripUser = tripUser;
        this.content = content;
    }

    public static ChatRoomMessage of(ChatRoom chatRoom ,
                                     TripUser tripUser,
                                     String content) {
        return new ChatRoomMessage(chatRoom,
                tripUser,
                content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatRoomMessage that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
