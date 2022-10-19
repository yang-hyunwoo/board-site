package com.board.boardsite.repository.querydsl.user;

import com.board.boardsite.domain.user.EmailAuth;
import com.board.boardsite.domain.user.QEmailAuth;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import java.time.LocalDateTime;
import java.util.Optional;

public class EmailAuthCustomRepositoryImpl extends QuerydslRepositorySupport implements EmailAuthCustomRepository {

    public EmailAuthCustomRepositoryImpl() {
        super(EmailAuth.class);
    }

    @Override
    public Optional<EmailAuth> findValidAuthByEmail(String email, String authToken, LocalDateTime currentTime) {
        QEmailAuth emailAuth = QEmailAuth.emailAuth;
        return Optional.ofNullable(from(emailAuth)
                .where(emailAuth.authToken.eq(authToken),
                        emailAuth.email.eq(email),
                        emailAuth.expireDate.goe(currentTime),
                        emailAuth.expired.eq(false))
                .fetchFirst());
    }
}
