package com.board.boardsite.dto.response.travel;

import com.board.boardsite.domain.travel.TravelAgencyLike;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.dto.travel.TravelAgencyListDto;
import com.board.boardsite.dto.travel.TravelAgencyListOnlyListDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public record TravelAgencyListResponse(
        Long id,
        Long travel_agency_id,
        String travelAgencyName,
        String city,
        String title,
        String content,
        int sale_percent,
        int real_paid,
        int sale_paid,
        String travel_start_at,
        String travel_end_at,
        String travelRealTripAt,
        int read_count,
        int person_count,
        int person_max_count,
        Long thumnbnailFileId,
        String filePath,
        int like_count,
        boolean deleted,
        Integer sort,
        AtomicBoolean auth
) {

    public static TravelAgencyListResponse of(Long id,
                                    Long travel_agency_id,
                                    String travelAgencyName,
                                    String city,
                                    String title,
                                    String content,
                                    int sale_percent,
                                    int real_paid,
                                    int sale_paid,
                                    String travel_start_at,
                                    String travel_end_at,
                                    String travelRealTripAt,
                                    int read_count,
                                    int person_count,
                                    int person_max_count,
                                    Long thumnbnailFileId,
                                    String filePath,
                                    int like_count,
                                    boolean deleted,
                                    Integer sort,
                                    AtomicBoolean auth) {
        return new TravelAgencyListResponse(
                id,
                travel_agency_id,
                travelAgencyName,
                city,
                title,
                content,
                sale_percent,
                real_paid,
                sale_paid,
                travel_start_at,
                travel_end_at,
                travelRealTripAt,
                read_count,
                person_count,
                person_max_count,
                thumnbnailFileId,
                filePath,
                like_count,
                deleted,
                sort,
                auth);
    }

    public static TravelAgencyListResponse from(TravelAgencyListDto dto){
        Long authChkLong = 0L;
        AtomicBoolean chk = new AtomicBoolean(false);
        if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            var articleAuthChk = Optional.ofNullable(SecurityContextHolder.getContext())
                    .map(SecurityContext::getAuthentication)
                    .map(Authentication::getPrincipal)
                    .map(TripUserPrincipal.class::cast);
            authChkLong = articleAuthChk.get().id();
        }

        Long vaildAuth = authChkLong;
        dto.travelAgencyLike().stream().map(TravelAgencyLike::getTripUser).forEach(s-> {if (s.getId().equals(vaildAuth)) {
            chk.set(true);
            return ;
            }
        });
        return new TravelAgencyListResponse(
                dto.id(),
                dto.travelAgency().getId(),
                dto.travelAgency().getName(),
                dto.city(),
                dto.title(),
                dto.content(),
                dto.sale_percent(),
                dto.real_paid(),
                dto.sale_paid(),
                dto.travel_start_at(),
                dto.travel_end_at(),
                dto.travelRealTripAt(),
                dto.read_count(),
                dto.person_count(),
                dto.person_max_count(),
                dto.thumnbnailFileId(),
                null,
                dto.like_count(),
                dto.deleted(),
                dto.sort(),
                chk
        );

    }


    public static TravelAgencyListResponse from(TravelAgencyListOnlyListDto dto){
        AtomicBoolean chk = new AtomicBoolean(false);
        return new TravelAgencyListResponse(
                dto.getId(),
                dto.getTravel_agency_id(),
                dto.getTravel_name(),
                dto.getCity(),
                dto.getTitle(),
                dto.getContent(),
                dto.getSale_percent(),
                dto.getReal_paid(),
                dto.getSale_paid(),
                dto.getTravel_start_at(),
                dto.getTravel_end_at(),
                dto.getTravelRealTripAt(),
                dto.getRead_count(),
                dto.getPerson_count(),
                dto.getPerson_max_count(),
                dto.getThumnbnailFileId(),
                dto.getFilePath(),
                dto.getLike_count(),
                dto.isDeleted(),
                dto.getSort(),
                chk
        );

    }

}
