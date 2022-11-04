package com.board.boardsite.dto.travel;

import com.board.boardsite.domain.travel.TravelAgencyList;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.user.TripUserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@NoArgsConstructor
@Getter
@Setter
public class TravelAgencyLikeOnlyDto{
        Long id;
        TripUser tripUser;
        String filePath;
        TravelAgencyList travelAgencyList;
        boolean deleted;
        LocalDateTime createdAt;
        String createdBy;
        LocalDateTime modifiedAt;
        String modifiedBy;


    public TravelAgencyLikeOnlyDto(Long id,
                                   TripUser tripUser,
                                   String filePath,
                                   TravelAgencyList travelAgencyList,
                                   boolean deleted,
                                   LocalDateTime createdAt,
                                   String createdBy,
                                   LocalDateTime modifiedAt,
                                   String modifiedBy) {
        this.id = id;
        this.tripUser = tripUser;
        this.filePath = filePath;
        this.travelAgencyList = travelAgencyList;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
    }
}
