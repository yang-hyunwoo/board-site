package com.board.boardsite.repository.travel;

import com.board.boardsite.domain.travel.TravelAgency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelAgencyRepository extends JpaRepository<TravelAgency , Long> {

    Page<TravelAgency> findByNameContaining(String TravelAgencyName , Pageable pageable);
}
