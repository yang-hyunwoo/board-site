package com.board.boardsite.domain.chat;

import com.board.boardsite.domain.AuditingFields;
import com.board.boardsite.domain.article.Article;
import com.board.boardsite.domain.user.TripUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Entity
public class ChatRoomPerson  extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(optional = false)
    private ChatRoom chatRoom;

    @Setter
    @ManyToOne(optional = false)
    private TripUser tripUser; // 유저 정보 (ID)

    protected ChatRoomPerson() {}

    private ChatRoomPerson(ChatRoom chatRoom , TripUser tripUser){
        this.chatRoom = chatRoom;
        this.tripUser = tripUser;
    }

    public static ChatRoomPerson of(ChatRoom chatRoom , TripUser tripUser) {
        return new ChatRoomPerson(chatRoom, tripUser);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatRoomPerson that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
