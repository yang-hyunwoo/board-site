package com.board.boardsite.repository.adm.travel;

import com.board.boardsite.domain.travel.TravelAgencyReservation;
import com.board.boardsite.repository.querydsl.travel.adm.travel.TravelAgencyReservationCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdmTravelAgencyReservationRepository extends JpaRepository<TravelAgencyReservation,Long> {

    Optional<TravelAgencyReservation> findByImpUidAndMerchantUid(String imp_uid , String merchantUid);

    Page<TravelAgencyReservation> findByTripUser_Id(Pageable pageable , Long tripUserId);

    Page<TravelAgencyReservation> findByTravelAgencyList_Id( Long travelAgencyListId , Pageable pageable);



    Page<TravelAgencyReservation> findByTravelAgencyList_IdAndTravelAgency_Id( Long travelAgencyListId ,Long travelAgencyId, Pageable pageable);

}
