package com.board.boardsite.repository.user;

import com.board.boardsite.domain.user.TripUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TripUserRepository extends JpaRepository<TripUser, Long> {

    Optional<TripUser> findByEmail(String email);

    Optional<TripUser> findByEmailAndEmailAuthAndDeleted(String email,boolean emailAuth,boolean deleted);
}
