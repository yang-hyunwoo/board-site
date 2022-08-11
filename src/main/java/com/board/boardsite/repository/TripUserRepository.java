package com.board.boardsite.repository;

import com.board.boardsite.domain.TripUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripUserRepository extends JpaRepository<TripUser , Long> {
}
