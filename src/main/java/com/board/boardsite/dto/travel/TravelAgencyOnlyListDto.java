package com.board.boardsite.dto.travel;

import com.board.boardsite.domain.travel.TravelAgency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class TravelAgencyOnlyListDto{
        Long id;
        String name;
        String address;
        String tel;
        String detail;
        Long fileId;
        String filePath;
        String comment;
        boolean deleted;
        LocalDateTime createdAt;
        String createdBy;
        LocalDateTime modifiedAt;
        String modifiedBy;

    public TravelAgencyOnlyListDto(Long id,
                                   String name,
                                   String address,
                                   String tel,
                                   String detail,
                                   Long fileId,
                                   String filePath,
                                   String comment,
                                   boolean deleted,
                                   LocalDateTime createdAt,
                                   String createdBy,
                                   LocalDateTime modifiedAt,
                                   String modifiedBy) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.detail = detail;
        this.fileId = fileId;
        this.filePath = filePath;
        this.comment = comment;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
    }

    public static TravelAgencyOnlyListDto from(TravelAgency entity) {
        return new TravelAgencyOnlyListDto(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getTel(),
                entity.getDetail(),
                entity.getFileId(),
                null,
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
