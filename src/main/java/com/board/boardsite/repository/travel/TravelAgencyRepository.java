package com.board.boardsite.repository.travel;

import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.repository.querydsl.travel.TravelAgencyCustomRespository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelAgencyRepository extends JpaRepository<TravelAgency , Long> ,
        TravelAgencyCustomRespository {

    Page<TravelAgency> findByNameContaining(String TravelAgencyName , Pageable pageable);
}
