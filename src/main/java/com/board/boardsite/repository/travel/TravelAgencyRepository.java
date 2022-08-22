package com.board.boardsite.repository.travel;

import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.domain.travel.TravelAgencyList;
import com.board.boardsite.repository.querydsl.travel.TravelAgencyCustomRespository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TravelAgencyRepository extends JpaRepository<TravelAgency , Long> ,
        TravelAgencyCustomRespository {

    Page<TravelAgency> findByNameContainingAndDeleted(String TravelAgencyName , Pageable pageable,boolean deleted);
    Page<TravelAgency> findAllByDeleted(Pageable pageable, boolean deleted);
    Optional<TravelAgency> findByIdAndDeleted(Long travelAgencyId , boolean deleted);
}
