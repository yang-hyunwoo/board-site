package com.board.boardsite.domain.user;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
public class EmailAuth {

    private static final Long MAX_EXPIRE_TIME = 5L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String authToken;
    private Boolean expired;
    private LocalDateTime expireDate;

    protected EmailAuth() {

    }

    private EmailAuth(String email,
                      String authToken,
                      Boolean expired,
                      LocalDateTime expireDate) {
        this.email = email;
        this.authToken = authToken;
        this.expired = expired;
        this.expireDate = expireDate;
    }

    public static EmailAuth of(String email,
                               String authToken,
                               Boolean expired,
                               LocalDateTime expireDate)

    {
        return new EmailAuth(email,
                authToken,
                expired,
                expireDate.plusMinutes(MAX_EXPIRE_TIME));
    }


    public void useToken() {
        this.expired = true;
    }
}
