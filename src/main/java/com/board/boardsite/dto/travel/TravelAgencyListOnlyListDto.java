package com.board.boardsite.dto.travel;

import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.domain.travel.TravelAgencyLike;
import com.board.boardsite.domain.travel.TravelAgencyList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class TravelAgencyListOnlyListDto{
        Long id;
        Long travel_agency_id;
        String travel_name;
        String city;
        String content;
        int person_count;
        int person_max_count;
        int read_count;
        int real_paid;
        int sale_paid;
        int sale_percent;
        Long thumnbnailFileId;
        String filePath;
        Integer sort;
        String title;
        String travel_start_at;
        String travel_end_at;
        String travelRealTripAt;
        LocalDateTime createdAt;
        String createdBy;
        LocalDateTime modifiedAt;
        String modifiedBy;
        TravelAgency travelAgency;
        int like_count;
        boolean deleted;
        boolean travelAgencyLike;

    public TravelAgencyListOnlyListDto(Long id,
                                       Long travel_agency_id,
                                       String travel_name,
                                       String city,
                                       String content,
                                       int person_count,
                                       int person_max_count,
                                       int read_count,
                                       int real_paid,
                                       int sale_paid,
                                       int sale_percent,
                                       Long thumnbnailFileId,
                                       String filePath,
                                       Integer sort,
                                       String title,
                                       String travel_start_at,
                                       String travel_end_at,
                                       String travelRealTripAt,
                                       LocalDateTime createdAt,
                                       String createdBy,
                                       LocalDateTime modifiedAt,
                                       String modifiedBy,
                                       TravelAgency travelAgency,
                                       int like_count,
                                       boolean deleted,
                                       boolean travelAgencyLike) {
        this.id = id;
        this.travel_agency_id = travel_agency_id;
        this.travel_name = travel_name;
        this.city = city;
        this.content = content;
        this.person_count = person_count;
        this.person_max_count = person_max_count;
        this.read_count = read_count;
        this.real_paid = real_paid;
        this.sale_paid = sale_paid;
        this.sale_percent = sale_percent;
        this.thumnbnailFileId = thumnbnailFileId;
        this.filePath =filePath;
        this.sort = sort;
        this.title = title;
        this.travel_start_at = travel_start_at;
        this.travel_end_at = travel_end_at;
        this.travelRealTripAt = travelRealTripAt;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
        this.travelAgency = travelAgency;
        this.like_count = like_count;
        this.deleted = deleted;
        this.travelAgencyLike = travelAgencyLike;
    }


}






