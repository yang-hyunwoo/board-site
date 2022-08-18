package com.board.boardsite.dto.travel;

import com.board.boardsite.domain.travel.TravelAgency;

import java.io.Serializable;
import java.time.LocalDateTime;

public record TravelAgencyDto(
        Long id,
        String name,
        String address,
        String tel,
        String detail,
        boolean deleted,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static TravelAgencyDto of(Long id,
                                     String name,
                                     String address,
                                     String tel,
                                     String detail,
                                     boolean deleted,
                                     LocalDateTime createdAt,
                                     String createdBy,
                                     LocalDateTime modifiedAt,
                                     String modifiedBy) {
        return new TravelAgencyDto(
                id,
                name,
                address,
                tel,
                detail,
                deleted,
                createdAt,
                createdBy,
                modifiedAt,
                modifiedBy);
    }

    public static TravelAgencyDto from(TravelAgency entity) {
        return new TravelAgencyDto(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getTel(),
                entity.getDetail(),
                entity.isDeleted(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public TravelAgency toEntity() {
        return TravelAgency.of(
                name,
                address,
                tel,
                detail,
                deleted
        );
    }

}
