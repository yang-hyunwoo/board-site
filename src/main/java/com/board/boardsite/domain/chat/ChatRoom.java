package com.board.boardsite.domain.chat;

import com.board.boardsite.domain.AuditingFields;
import com.board.boardsite.domain.article.Article;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Entity
@Where(clause = "deleted = false")
public class ChatRoom extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false , length = 100)
    private String roomName;

    @Setter
    private int roomCount;

    @Setter
    @Column(nullable = false)
    private boolean deleted;

    @Setter
    @Column(nullable = false)
    private boolean publicRoom;

    @Setter
    private int roomPersonIngCount;


    protected ChatRoom() {}

    private ChatRoom(String roomName ,
                     int roomCount ,
                     int roomPersonIngCount,
                     boolean deleted ,
                     boolean publicRoom){
        this.roomName = roomName;
        this.roomCount = roomCount;
        this.roomPersonIngCount = roomPersonIngCount;
        this.deleted = deleted;
        this.publicRoom = publicRoom;
    }

    public static ChatRoom of(String roomName ,
                              int roomCount ,
                              int roomPersonIngCount,
                              boolean deleted ,
                              boolean publicRoom) {
        return new ChatRoom(roomName,
                roomCount,
                roomPersonIngCount,
                deleted,
                publicRoom);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatRoom that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void personCountPlus(int roomPersonIngCount){
        this.roomPersonIngCount = roomPersonIngCount+1;
    }
}
