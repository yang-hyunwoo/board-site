package com.board.boardsite.repository;

import com.board.boardsite.domain.TravelAgency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelAgencyRepository extends JpaRepository<TravelAgency , Long> {
}
