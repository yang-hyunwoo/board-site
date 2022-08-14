package com.board.boardsite.repository.user;

import com.board.boardsite.domain.user.EmailAuth;
import com.board.boardsite.repository.querydsl.user.EmailAuthCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailAuthRepository extends JpaRepository<EmailAuth,Long>,
        EmailAuthCustomRepository {
}
