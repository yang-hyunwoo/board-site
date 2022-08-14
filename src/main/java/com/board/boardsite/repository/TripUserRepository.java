package com.board.boardsite.repository;

import com.board.boardsite.domain.TripUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TripUserRepository extends JpaRepository<TripUser , Long> {

    Optional<TripUser> findByEmail(String email);
}
