package com.board.boardsite.domain.chat;

import com.board.boardsite.domain.AuditingFields;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "채팅방 제목을 입력해주세요")
    @Column(nullable = false , length = 100)
    private String roomName;

    @Min(value = 2 , message = "최소 인원은 2명 입니다.")
    @Max(value = 300 , message = "최대 인원은 300명 입니다.")
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

    public void personCountMinus(int roomPersonIngCount){
        this.roomPersonIngCount = roomPersonIngCount-1;
    }
}
