package com.board.boardsite.repository.querydsl.travel.adm.travel;

import com.board.boardsite.dto.travel.TravelAgencyListOnlyListDto;
import com.board.boardsite.dto.travel.TravelAgencyOnlyListDto;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AdmTravelAgencyListCustomRepository {

    PageImpl<TravelAgencyListOnlyListDto> findCustomByCreatedAtBetweenOrderById(LocalDateTime startDate , LocalDateTime endDate , Pageable pageable);

    PageImpl<TravelAgencyListOnlyListDto> findCustomByTitleContainingAndCreatedAtBetweenOrderById(String input , LocalDateTime startDate , LocalDateTime endDate , Pageable pageable);

    PageImpl<TravelAgencyListOnlyListDto> findByCustomTravelAgencyNameContainingAndCreatedAtBetweenOrderById(String input , LocalDateTime startDate , LocalDateTime endDate , Pageable pageable);

    PageImpl<TravelAgencyListOnlyListDto> findCustomByTravelRealTripAtBetweenOrderById(String startDate , String endDate , Pageable pageable);

    PageImpl<TravelAgencyListOnlyListDto> findCustomByTitleContainingAndTravelRealTripAtBetweenOrderById(String input , String startDate , String endDate , Pageable pageable);

    PageImpl<TravelAgencyListOnlyListDto> findCustomByTravelAgencyNameContainingAndTravelRealTripAtBetweenOrderById(String input , String startDate , String endDate , Pageable pageable);

    PageImpl<TravelAgencyListOnlyListDto> findCustomByTravelAgency_IdAndCreatedAtBetweenOrderById(Long travelAgencyId , LocalDateTime startDate , LocalDateTime endDate , Pageable pageable);

    PageImpl<TravelAgencyListOnlyListDto> findCustomByTravelAgency_IdAndTitleContainingAndCreatedAtBetweenOrderById(Long travelAgencyId ,String input , LocalDateTime startDate , LocalDateTime endDate , Pageable pageable);
    PageImpl<TravelAgencyListOnlyListDto> findCustomByTravelAgency_IdAndTravelAgencyNameContainingAndCreatedAtBetweenOrderById(Long travelAgencyId ,String input , LocalDateTime startDate , LocalDateTime endDate , Pageable pageable);

    PageImpl<TravelAgencyListOnlyListDto> findCustomByTravelAgency_IdAndTravelRealTripAtBetweenOrderById(Long travelAgencyId , String startDate , String endDate , Pageable pageable);

    PageImpl<TravelAgencyListOnlyListDto> findCustomByTravelAgency_IdAndTitleContainingAndTravelRealTripAtBetweenOrderById(Long travelAgencyId ,String input, String startDate , String endDate , Pageable pageable);

    PageImpl<TravelAgencyListOnlyListDto> findCustomByTravelAgency_IdAndTravelAgencyNameContainingAndTravelRealTripAtBetweenOrderById(Long travelAgencyId ,String input, String startDate , String endDate , Pageable pageable);




}
