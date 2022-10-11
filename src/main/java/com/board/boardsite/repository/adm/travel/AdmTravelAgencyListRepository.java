package com.board.boardsite.repository.adm.travel;

import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.domain.travel.TravelAgencyLike;
import com.board.boardsite.domain.travel.TravelAgencyList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmTravelAgencyListRepository extends JpaRepository<TravelAgencyList, Long> {

        long countByTravelAgencyIdAndDeleted(Long travelAgencyId , boolean deleted);
}
