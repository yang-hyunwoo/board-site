package com.board.boardsite.repository.user;

import com.board.boardsite.domain.user.TripUserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripUserHistoryRepository extends JpaRepository<TripUserHistory, Long> {

    List<TripUserHistory> findByTripUserId(Long userId);
}
