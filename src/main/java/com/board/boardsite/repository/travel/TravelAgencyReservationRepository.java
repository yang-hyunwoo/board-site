package com.board.boardsite.repository.travel;

import com.board.boardsite.domain.travel.TravelAgencyReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TravelAgencyReservationRepository extends JpaRepository<TravelAgencyReservation,Long> {

    Optional<TravelAgencyReservation> findByImpUidAndMerchantUid(String imp_uid , String merchantUid);

}
