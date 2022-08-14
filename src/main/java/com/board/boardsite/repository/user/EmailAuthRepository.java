package com.board.boardsite.repository.user;

import com.board.boardsite.domain.user.EmailAuth;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.repository.querydsl.user.EmailAuthCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailAuthRepository extends JpaRepository<EmailAuth,Long>,
        EmailAuthCustomRepository {

    Optional<EmailAuth> findByEmail(String email);
}
