package com.board.boardsite.repository.travel;

import com.board.boardsite.domain.travel.TravelAgencyReservation;
import com.board.boardsite.repository.querydsl.travel.adm.travel.TravelAgencyReservationCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TravelAgencyReservationRepository extends JpaRepository<TravelAgencyReservation,Long>
        , TravelAgencyReservationCustomRepository {

    Optional<TravelAgencyReservation> findByImpUidAndMerchantUid(String imp_uid , String merchantUid);

    Page<TravelAgencyReservation> findByTripUser_Id(Pageable pageable , Long tripUserId);

}
