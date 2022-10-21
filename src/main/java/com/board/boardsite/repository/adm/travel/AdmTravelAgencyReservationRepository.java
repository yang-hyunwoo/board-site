package com.board.boardsite.repository.adm.travel;

import com.board.boardsite.domain.travel.TravelAgencyReservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdmTravelAgencyReservationRepository extends JpaRepository<TravelAgencyReservation,Long> {

    Optional<TravelAgencyReservation> findByImpUidAndMerchantUid(String imp_uid , String merchantUid);

    Page<TravelAgencyReservation> findByTripUser_Id(Pageable pageable , Long tripUserId);

    Page<TravelAgencyReservation> findByTravelAgencyList_Id( Long travelAgencyListId , Pageable pageable);

    List<TravelAgencyReservation> findByTravelAgencyList_IdAndDeleted(Long travelAgencyListId , boolean deleted);
    List<TravelAgencyReservation> findByTravelAgencyList_Id(Long travelAgencyListId);

    Page<TravelAgencyReservation> findByTravelAgencyList_IdAndTravelAgency_Id( Long travelAgencyListId ,Long travelAgencyId, Pageable pageable);

    Page<TravelAgencyReservation> findByTravelAgencyList_IdAndDeleted(Long travelAgencyListId , boolean deleted,Pageable pageable);

    Page<TravelAgencyReservation> findByTripUser_IdAndTravelAgency_Id(Long userId ,Long travelAgencyId, Pageable pageable);

}
