package com.board.boardsite.repository.travel;

import com.board.boardsite.domain.travel.TravelAgencyList;
import com.board.boardsite.repository.querydsl.travel.TravelAgencyListCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TravelAgencyListRepository extends JpaRepository<TravelAgencyList, Long> , TravelAgencyListCustomRepository {

    List<TravelAgencyList> findByTravelAgencyIdAndDeletedOrderByTitleAsc(Long travelAgencyId , boolean deleted);

    Page<TravelAgencyList> findByTitleContainingAndDeleted(String travelAgencyTitleName , Pageable pageable , boolean deleted);

    Optional<TravelAgencyList> findByIdAndDeleted(Long travelAgencyId , boolean deleted);
    Page<TravelAgencyList> findAllByDeleted(Pageable pageable ,boolean deleted);



    List<TravelAgencyList> findByTravelEndAtLessThanEqualAndDeleted(String endDe , boolean deleted);

    List<TravelAgencyList> findByTravelAgencyId(Long travelAgencyId);

}
