package com.board.boardsite.repository.adm.travel;

import com.board.boardsite.domain.travel.TravelAgencyList;
import com.board.boardsite.repository.querydsl.travel.adm.travel.AdmTravelAgencyListCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AdmTravelAgencyListRepository extends JpaRepository<TravelAgencyList, Long> , AdmTravelAgencyListCustomRepository {

        List<TravelAgencyList> findByTravelAgency_Id(Long travelAgencyId);




        Page<TravelAgencyList> findByTravelAgency_IdAndTravelRealTripAtBetweenOrderById(Long agencyId , String startDate , String endDate , Pageable pageable );


        Page<TravelAgencyList> findByTravelRealTripAtBetweenOrderById(String startDate , String endDate , Pageable pageable );




        Optional<TravelAgencyList> findByIdAndDeleted(Long travelAgencyListId , boolean deleted);

        Optional<TravelAgencyList> findBySort(int sort);

        Optional<TravelAgencyList> findByIdAndTravelRealTripAtBetweenAndDeleted(Long travelAgencyListId, String startDate, String endDate, boolean deleted);


        long countByTravelAgencyIdAndDeleted(Long travelAgencyId , boolean deleted);
}
