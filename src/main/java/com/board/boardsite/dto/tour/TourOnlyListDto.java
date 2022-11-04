package com.board.boardsite.dto.tour;

import com.board.boardsite.domain.tour.Tour;
import com.board.boardsite.domain.user.TripUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class TourOnlyListDto {
    Long id;
    TripUser tripUser;
    String title;
    String content;
    boolean deleted;
    int readCount;
    Long thumbnailId;
    String filePath;
    String city;
    LocalDateTime createdAt;
    String createdBy;
    LocalDateTime modifiedAt;
    String modifiedBy;

    public TourOnlyListDto(Long id,
                           TripUser tripUser,
                           String title,
                           String content,
                           boolean deleted,
                           int readCount,
                           Long thumbnailId,
                           String filePath,
                           String city,
                           LocalDateTime createdAt,
                           String createdBy,
                           LocalDateTime modifiedAt,
                           String modifiedBy) {
        this.id = id;
        this.tripUser = tripUser;
        this.title = title;
        this.content = content;
        this.deleted = deleted;
        this.readCount = readCount;
        this.thumbnailId = thumbnailId;
        this.filePath = filePath;
        this.city = city;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
    }





    public static TourOnlyListDto from(Tour entity){
        return new TourOnlyListDto(
                entity.getId(),
                entity.getTripUser(),
                entity.getTitle(),
                entity.getContent(),
                entity.isDeleted(),
                entity.getReadCount(),
                entity.getThumbnailId(),
                null,
                entity.getCity(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public static TourOnlyListDto of(
            TripUser tripUser,
            String title,
            String content,
            Long thumbnailId,
            String city) {
        return new TourOnlyListDto(null,
                tripUser,
                title,
                content,
                false,
                0,
                thumbnailId,
                null,
                city,
                null,
                null,
                null,
                null);
    }
    public Tour toEntity(TripUser tripUser) {
        return Tour.of(
                title,
                content,
                deleted,
                readCount,
                city,
                thumbnailId,
                tripUser
        );
    }

}