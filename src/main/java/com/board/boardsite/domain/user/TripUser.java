package com.board.boardsite.domain.user;


import com.board.boardsite.domain.ArticleComment;
import com.board.boardsite.domain.AuditingFields;
import com.board.boardsite.domain.constant.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Entity
@Table(indexes = {
        @Index(columnList = "nickname"),
        @Index(columnList = "email"),
        @Index(columnList = "name"),
        @Index(columnList = "point")
})
//@EntityListeners(value = {TripUserEntityListener.class})
public class TripUser extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                        //ID

    @Setter
    @Column(nullable = false , length = 50)
    private String name;                                    //이름

    @Setter
    @Column(nullable = false , length = 50)
    private String nickName;                                    //이름

    @Setter
    @Column(nullable = false , length = 100)
    private String email;                                   //이메일

    @Setter
    @Column(nullable = false , length = 1000)
    private String password;                                //패스워트

    private int point;
    //포인트
    @Setter
    @Column(nullable = false , length = 1)
    private String useYn;

    @Enumerated(value=EnumType.STRING)
    private Gender gender;                                  //성별

    private Boolean emailAuth;

    private String refreshToken;

    protected TripUser() {

    }

    private TripUser(String name,
                     String nickName,
                     String email,
                     String password,
                     int point,
                     Gender gender,
                     String useYn,
                     Boolean emailAuth)
    {
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.point = point;
        this.gender = gender;
        this.useYn = useYn;
        this.emailAuth = emailAuth;
    }

    public static TripUser of(String name,
                              String nickName,
                              String email,
                              String password,
                              int point,
                              Gender gender,
                              String useYn,
                              Boolean emailAuth)
    {
      return new TripUser(name,
                          nickName,
                          email,
                          password,
                          point,
                          gender,
                          useYn,
                          emailAuth);
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    public void emailVerifiedSuccess(){
        this.emailAuth = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
