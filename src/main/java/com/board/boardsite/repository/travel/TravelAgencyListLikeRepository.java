package com.board.boardsite.repository.travel;

import com.board.boardsite.domain.travel.TravelAgencyLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TravelAgencyListLikeRepository extends JpaRepository<TravelAgencyLike , Long> {

    Optional<TravelAgencyLike> findByTravelAgencyList_IdAndTripUser_Id(Long travel_agency_id, Long trip_user_id);
}
