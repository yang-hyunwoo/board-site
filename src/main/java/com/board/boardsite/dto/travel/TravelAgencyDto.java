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
        Long fileId,

        String comment,
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
                                     Long fileId,
                                     String comment,
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
                fileId,
                comment,
                deleted,
                createdAt,
                createdBy,
                modifiedAt,
                modifiedBy);
    }


    public static TravelAgencyDto of(String name,
                                     String address,
                                     String tel,
                                     String detail,
                                     Long fileId,
                                     String comment) {
        return new TravelAgencyDto(
                null,
                name,
                address,
                tel,
                detail,
                fileId,
                comment,
                false,
                null,
                null,
                null,
                null);
    }

    public static TravelAgencyDto from(TravelAgency entity) {
        return new TravelAgencyDto(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getTel(),
                entity.getDetail(),
                entity.getFileId(),
                entity.getComment(),
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
                comment,
                fileId,
                deleted
        );
    }

}
