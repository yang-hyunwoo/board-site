package com.board.boardsite.repository.travel;

import com.board.boardsite.domain.travel.TravelAgencyList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TravelAgencyListRepository extends JpaRepository<TravelAgencyList, Long> {

    List<TravelAgencyList> findByTravelAgencyIdAndDeletedOrderByTitleAsc(Long travelAgencyId , boolean deleted);

    Page<TravelAgencyList> findByTitleContaining(String travelAgencyTitleName , Pageable pageable);

    Optional<TravelAgencyList> findByIdAndDeleted(Long travelAgencyId , boolean deleted);

}
