package com.board.boardsite.domain.user;

import com.board.boardsite.domain.Article;
import lombok.Getter;
import lombok.Setter;

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

    @Setter
    private String authToken;
    private Boolean expired;
    @Setter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailAuth that)) return false;
        return email != null && email.equals(that.getEmail());
    }

    public void useToken() {
        this.expired = true;
    }
}
