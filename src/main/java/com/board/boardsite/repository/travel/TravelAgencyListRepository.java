package com.board.boardsite.repository.travel;

import com.board.boardsite.domain.travel.TravelAgencyList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TravelAgencyListRepository extends JpaRepository<TravelAgencyList, Long> {

    List<TravelAgencyList> findByTravelAgencyIdAndDeletedOrderByTitleAsc(Long travelAgencyId , boolean deleted);

}
