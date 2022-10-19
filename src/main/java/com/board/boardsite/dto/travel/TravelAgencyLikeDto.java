package com.board.boardsite.dto.travel;

import com.board.boardsite.domain.travel.TravelAgencyLike;
import com.board.boardsite.domain.travel.TravelAgencyList;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.user.TripUserDto;
import java.time.LocalDateTime;

public record TravelAgencyLikeDto(
        Long id,
        TripUserDto tripUser,
        TravelAgencyListDto travelAgencyList,
        boolean deleted,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static TravelAgencyLikeDto of(Long id,
                               TripUserDto tripUser,
                               TravelAgencyListDto travelAgencyList,
                               boolean deleted,
                               LocalDateTime createdAt,
                               String createdBy,
                               LocalDateTime modifiedAt,
                               String modifiedBy) {
        return new TravelAgencyLikeDto(
                id,
                tripUser,
                travelAgencyList,
                deleted,
                createdAt,
                createdBy,
                modifiedAt,
                modifiedBy);
    }

    public static TravelAgencyLikeDto from(TravelAgencyLike entity) {
        return new TravelAgencyLikeDto(
                entity.getId(),
                TripUserDto.from(entity.getTripUser()),
                TravelAgencyListDto.from(entity.getTravelAgencyList()),
                entity.isDeleted(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public TravelAgencyLike toEntity(TripUser tripUser ,
                                     TravelAgencyList travelAgencyList) {
        return TravelAgencyLike.of(
                tripUser,
                travelAgencyList,
                deleted
        );
    }
}
