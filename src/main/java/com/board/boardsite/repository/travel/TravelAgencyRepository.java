package com.board.boardsite.repository.travel;

import com.board.boardsite.domain.travel.TravelAgency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelAgencyRepository extends JpaRepository<TravelAgency , Long> {
}
