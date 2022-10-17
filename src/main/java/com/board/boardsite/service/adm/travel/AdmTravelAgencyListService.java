package com.board.boardsite.service.adm.travel;


import com.board.boardsite.domain.constant.SearchAdmTravelListType;
import com.board.boardsite.domain.tour.Tour;
import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.domain.travel.TravelAgencyList;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.dto.tour.TourDto;
import com.board.boardsite.dto.travel.TravelAgencyListDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.adm.travel.AdmTravelAgencyListRepository;
import com.board.boardsite.repository.adm.travel.AdmTravelAgencyRepository;
import com.board.boardsite.repository.travel.TravelAgencyListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdmTravelAgencyListService {

    private final AdmTravelAgencyListRepository admTravelAgencyListRepository;

    private final AdmTravelAgencyRepository admTravelAgencyRepository;

    @Transactional(readOnly = true)
    public  Page<TravelAgencyListDto> travelAgencyTripList(SearchAdmTravelListType inputSearch,
                                                           SearchAdmTravelListType dateSearch,
                                                           String startAt,
                                                           String endAt,
                                                           String input,
                                                           TripUserPrincipal tripUserPrincipal,
                                                           Pageable pageable) {
        //작성일 기준
        String localDateStartDate = startAt+" 00:00:00.000000";
        String localDateStartDate2 = endAt+" 23:59:59.999999";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        LocalDateTime dateTime = LocalDateTime.parse(localDateStartDate, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(localDateStartDate2, formatter);
        if(tripUserPrincipal.role().equals("SUPER")) {
            //작성일 기준
            if (dateSearch.toString().equals("CREATEDAT")) {
                if (input == null || input.isBlank()) {
                    return admTravelAgencyListRepository.findByCreatedAtBetweenOrderById(dateTime, dateTime2, pageable).map(TravelAgencyListDto::from);
                } else {
                    if (inputSearch.toString().equals("TITLE")) { //제목으로 검색 시
                        return admTravelAgencyListRepository.findByTitleContainingAndCreatedAtBetweenOrderById(input, dateTime, dateTime2, pageable).map(TravelAgencyListDto::from);
                    } else {    //여행사 이름으로 검색 시
                        return admTravelAgencyListRepository.findByTravelAgencyNameContainingAndCreatedAtBetweenOrderById(input, dateTime, dateTime2, pageable).map(TravelAgencyListDto::from);
                    }
                }
                //출발일 기준
            } else {
                if (input == null || input.isBlank()) {
                    return admTravelAgencyListRepository.findByTravelRealTripAtBetweenOrderById(startAt.replaceAll("-", ""), endAt.replaceAll("-", ""), pageable).map(TravelAgencyListDto::from);
                } else {
                    if (inputSearch.toString().equals("TITLE")) {
                        return admTravelAgencyListRepository.findByTitleContainingAndTravelRealTripAtBetweenOrderById(input, startAt.replaceAll("-", ""), endAt.replaceAll("-", ""), pageable).map(TravelAgencyListDto::from);
                    } else {
                        return admTravelAgencyListRepository.findByTravelAgencyNameContainingAndTravelRealTripAtBetweenOrderById(input, startAt.replaceAll("-", ""), endAt.replaceAll("-", ""), pageable).map(TravelAgencyListDto::from);
                    }
                }
            }
        }else {
            //작성일 기준
            if (dateSearch.toString().equals("CREATEDAT")) {
                if (input == null || input.isBlank()) {
                    return admTravelAgencyListRepository.findByTravelAgency_IdAndCreatedAtBetweenOrderById(tripUserPrincipal.travelAgencyId(),dateTime, dateTime2, pageable).map(TravelAgencyListDto::from);
                } else {
                    if (inputSearch.toString().equals("TITLE")) { //제목으로 검색 시
                        return admTravelAgencyListRepository.findByTravelAgency_IdAndTitleContainingAndCreatedAtBetweenOrderById(tripUserPrincipal.travelAgencyId(),input, dateTime, dateTime2, pageable).map(TravelAgencyListDto::from);
                    } else {    //여행사 이름으로 검색 시
                        return admTravelAgencyListRepository.findByTravelAgency_IdAndTravelAgencyNameContainingAndCreatedAtBetweenOrderById(tripUserPrincipal.travelAgencyId() , input, dateTime, dateTime2, pageable).map(TravelAgencyListDto::from);
                    }
                }
                //출발일 기준
            } else {
                if (input == null || input.isBlank()) {
                    return admTravelAgencyListRepository.findByTravelAgency_IdAndTravelRealTripAtBetweenOrderById(tripUserPrincipal.travelAgencyId(), startAt.replaceAll("-", ""), endAt.replaceAll("-", ""), pageable).map(TravelAgencyListDto::from);
                } else {
                    if (inputSearch.toString().equals("TITLE")) {
                        return admTravelAgencyListRepository.findByTravelAgency_IdAndTitleContainingAndTravelRealTripAtBetweenOrderById(tripUserPrincipal.travelAgencyId(),input, startAt.replaceAll("-", ""), endAt.replaceAll("-", ""), pageable).map(TravelAgencyListDto::from);
                    } else {
                        return admTravelAgencyListRepository.findByTravelAgency_IdAndTravelAgencyNameContainingAndTravelRealTripAtBetweenOrderById(tripUserPrincipal.travelAgencyId(),input, startAt.replaceAll("-", ""), endAt.replaceAll("-", ""), pageable).map(TravelAgencyListDto::from);
                    }
                }
            }
        }
    }

    @Transactional
    public void deleteTravelAgencyList(Long travelAgencyListId) {
        //TODO: 여행리스트 삭제시 결제 내역 체크 후 취소를 막아야 될까??... 현재 그냥 삭제되게 해놓음

        TravelAgencyList travelAgencyList = admTravelAgencyListRepository.findByIdAndDeleted(travelAgencyListId,false).orElseThrow(()->new BoardSiteException(ErrorCode.TRAVEL_AGENCY_LIST_NOT_FOUND));
        travelAgencyList.setDeleted(true);
    }

    @Transactional
    public void reDeleteTravelAgencyList(Long travelAgencyListId) {
        //TODO: 여행리스트 삭제시 결제 내역 체크 후 취소를 막아야 될까??... 현재 그냥 삭제되게 해놓음

        TravelAgencyList travelAgencyList = admTravelAgencyListRepository.findByIdAndDeleted(travelAgencyListId,true).orElseThrow(()->new BoardSiteException(ErrorCode.TRAVEL_AGENCY_LIST_NOT_FOUND));
        travelAgencyList.setDeleted(false);
    }

    @Transactional
    public void saveTravelAgencyList(TravelAgencyListDto dto){
        TravelAgency travelAgency = admTravelAgencyRepository.getReferenceById(dto.travel_agency_id());
        admTravelAgencyListRepository.save(dto.toEntity(travelAgency));
    }

    @Transactional
    public TravelAgencyListDto travelAgencyListDetail(Long travelAgencyListId , TripUserPrincipal tripUserPrincipal){
        if(tripUserPrincipal.role().equals("SUPER")) {
            var travelAgencyListDetail = admTravelAgencyListRepository.findById(travelAgencyListId).orElseThrow(() -> new BoardSiteException(ErrorCode.TRAVEL_AGENCY_DETAIL_NOT_FOUND));

            return Optional.of(travelAgencyListDetail).map(TravelAgencyListDto::from).orElseThrow(() -> new BoardSiteException(ErrorCode.TRAVEL_AGENCY_LIST_NOT_FOUND));
        } else {
            var travelAgencyListDetail = admTravelAgencyListRepository.findById(travelAgencyListId).orElseThrow(() -> new BoardSiteException(ErrorCode.TRAVEL_AGENCY_DETAIL_NOT_FOUND));
            if(travelAgencyListDetail.getTravelAgency().getId().equals(tripUserPrincipal.travelAgencyId())){
                throw new BoardSiteException(ErrorCode.NOT_PERMITTION);
            }

            return Optional.of(travelAgencyListDetail).map(TravelAgencyListDto::from).orElseThrow(() -> new BoardSiteException(ErrorCode.TRAVEL_AGENCY_LIST_NOT_FOUND));

        }
    }


    @Transactional
    public void updateTravelAgencyList(Long TravelAgencyListId , TravelAgencyListDto dto){

        TravelAgencyList travelAgencyList = admTravelAgencyListRepository.findById(TravelAgencyListId).orElseThrow(()->new BoardSiteException(ErrorCode.TRAVEL_AGENCY_LIST_NOT_FOUND));
        TravelAgency travelAgency = admTravelAgencyRepository.findById(dto.travel_agency_id()).orElseThrow(()->new BoardSiteException(ErrorCode.TRAVEL_AGENCY_LIST_NOT_FOUND));
        travelAgencyList.setTravelAgency(travelAgency);
        travelAgencyList.setCity(dto.city());
        travelAgencyList.setContent(dto.content());
        travelAgencyList.setPersonMaxCount(dto.person_max_count());
        travelAgencyList.setRealPaid(dto.real_paid());
        travelAgencyList.setSalePaid(dto.sale_paid());
        travelAgencyList.setSalePercent(dto.sale_percent());
        travelAgencyList.setTitle(dto.title());
        travelAgencyList.setTravelEndAt(dto.travel_end_at());
        travelAgencyList.setTravelRealTripAt(dto.travelRealTripAt());
        travelAgencyList.setThumnbnailFileId(dto.thumnbnailFileId());
    }

    @Transactional
    public void sortTravelAgencyList(Long travelAgencyListId , int sort){
        if(sort==0) {
            var newTravelAgencyList =  admTravelAgencyListRepository.findById(travelAgencyListId).orElseThrow(()->new BoardSiteException(ErrorCode.TRAVEL_AGENCY_DETAIL_NOT_FOUND));
            newTravelAgencyList.setSort(null);
        } else {
            var oldTravelAgencyList =  admTravelAgencyListRepository.findBySort(sort);
            oldTravelAgencyList.ifPresent(travelAgencyList ->
            {
                travelAgencyList.setSort(null);
            });

            var newTravelAgencyList =  admTravelAgencyListRepository.findById(travelAgencyListId).orElseThrow(()->new BoardSiteException(ErrorCode.TRAVEL_AGENCY_DETAIL_NOT_FOUND));
            newTravelAgencyList.setSort(sort);
        }


    }


}
