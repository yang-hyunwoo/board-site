package com.board.boardsite.repository.adm.travel;

import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.domain.travel.TravelAgencyList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AdmTravelAgencyListRepository extends JpaRepository<TravelAgencyList, Long>  {

        Page<TravelAgencyList> findByCreatedAtBetweenOrderById(LocalDateTime startDate , LocalDateTime endDate , Pageable pageable );

        Page<TravelAgencyList> findByTitleContainingAndCreatedAtBetweenOrderById(String title , LocalDateTime startDate , LocalDateTime endDate , Pageable pageable );

        Page<TravelAgencyList> findByTravelAgencyNameContainingAndCreatedAtBetweenOrderById(String title , LocalDateTime startDate , LocalDateTime endDate , Pageable pageable );

        Page<TravelAgencyList> findByTravelRealTripAtBetweenOrderById(String startDate , String endDate , Pageable pageable );


        Page<TravelAgencyList> findByTitleContainingAndTravelRealTripAtBetweenOrderById(String title , String startDate , String endDate , Pageable pageable );

        Page<TravelAgencyList> findByTravelAgencyNameContainingAndTravelRealTripAtBetweenOrderById(String title , String startDate , String endDate , Pageable pageable );

        Optional<TravelAgencyList> findByIdAndDeleted(Long travelAgencyListId , boolean deleted);

        Optional<TravelAgencyList> findBySort(int sort);


        long countByTravelAgencyIdAndDeleted(Long travelAgencyId , boolean deleted);
}
