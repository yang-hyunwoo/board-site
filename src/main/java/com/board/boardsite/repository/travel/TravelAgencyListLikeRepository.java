package com.board.boardsite.repository.travel;

import com.board.boardsite.domain.travel.TravelAgencyLike;
import com.board.boardsite.repository.querydsl.travel.TravelAgencyLikeCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TravelAgencyListLikeRepository extends JpaRepository<TravelAgencyLike , Long> , TravelAgencyLikeCustomRepository {

    Optional<TravelAgencyLike> findByTravelAgencyList_IdAndTripUser_Id(Long travel_agency_id, Long trip_user_id);

    Page<TravelAgencyLike> findByTripUser_Id(Long id, Pageable pageable);
}
