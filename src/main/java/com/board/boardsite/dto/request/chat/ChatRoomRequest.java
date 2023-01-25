package com.board.boardsite.dto.request.chat;

import com.board.boardsite.dto.chat.ChatRoomDto;
import io.swagger.annotations.ApiModelProperty;

public record ChatRoomRequest(

        @ApiModelProperty(value ="공개여부" , example = "true" , required = true)
        Boolean publicRoom,

        @ApiModelProperty(value ="채팅방 인원수" , example = "1" , required = true)
        int roomCount,

        @ApiModelProperty(value ="채팅방 이름" , example = "test" , required = true)
        String roomName
) {
    public static ChatRoomRequest of(Boolean publicRoom,
                                     int roomCount,
                                     String roomName) {
        return new ChatRoomRequest(publicRoom,
                roomCount,
                roomName);
    }

    public ChatRoomDto toDto(){
        return ChatRoomDto.of(roomName,
                roomCount,
                publicRoom);
    }
}
