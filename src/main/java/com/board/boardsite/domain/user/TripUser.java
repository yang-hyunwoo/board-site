package com.board.boardsite.domain.user;


import com.board.boardsite.domain.AuditingFields;
import com.board.boardsite.domain.constant.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.*;
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
@Where(clause = "deleted = false")
//@EntityListeners(value = {TripUserEntityListener.class})
public class TripUser extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                        //ID

    @Setter
    @NotEmpty(message = "이름을 입력해주세요")
    @Column(nullable = false , length = 50)
    private String name;                                    //이름

    @Setter
    @NotEmpty(message = "닉네임을 입력해주세요")
    @Column(nullable = false , length = 50)
    private String nickName;                                    //이름

    @Setter
    @NotEmpty(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @Column(nullable = false , length = 100)
    private String email;                                   //이메일

    @Setter
    @NotEmpty(message = "패스워드를 입력해주세요.")
    @Size(min = 8 , message = "최소 8자리의 비밀번호가 필요합니다.")
    @Column(nullable = false , length = 1000)
    private String password;                                //패스워트

    @Setter
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$" , message = "휴대폰 번호의 양식과 맞지 않습니다.")
    @Column(nullable = false , length =20)
    private String phoneNumber;                             //휴대폰 번호

    private int point;
    //포인트
    @Setter
    @Column(nullable = false)
    private boolean deleted;

    @Enumerated(value=EnumType.STRING)
    @Setter
    private Gender gender;                                  //성별

    private Boolean emailAuth;

    private String refreshToken;

    @Setter
    private Long profileId;

    @Setter
    private boolean authChk;

    @Setter
    @Column(nullable = false)
    private String role;                                    //권한

    @Setter
    private Long travelAgencyId;

    protected TripUser() {

    }

    private TripUser(String name,
                     String nickName,
                     String email,
                     String password,
                     String phoneNumber,
                     int point,
                     Gender gender,
                     boolean deleted,
                     Long profileId,
                     Boolean emailAuth,
                     String role,
                     Long travelAgencyId,
                     boolean authChk)
    {
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.point = point;
        this.gender = gender;
        this.deleted = deleted;
        this.emailAuth = emailAuth;
        this.profileId = profileId;
        this.role = role;
        this.travelAgencyId = travelAgencyId;
        this.authChk = authChk;
    }

    public TripUser(Long id,
                    String name,
                    String nickName,
                    String email,
                    String password,
                    String phoneNumber,
                    int point,
                    Gender gender,
                    boolean deleted,
                    Long profileId,
                    Boolean emailAuth,
                    String role,
                    Long travelAgencyId,
                    boolean authChk) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.point = point;
        this.gender = gender;
        this.deleted = deleted;
        this.profileId = profileId;
        this.emailAuth = emailAuth;
        this.role =role;
        this.travelAgencyId = travelAgencyId;
        this.authChk =authChk;
    }

    public static TripUser of(String name,
                              String nickName,
                              String email,
                              String password,
                              String phoneNumber,
                              int point,
                              Gender gender,
                              boolean deleted,
                              Long profileId,
                              Boolean emailAuth,
                              String role,
                              Long travelAgencyId,
                              boolean authChk)
    {
      return new TripUser(name,
                          nickName,
                          email,
                          password,
                          phoneNumber,
                          point,
                          gender,
                          deleted,
                          profileId,
                          emailAuth,
                          role,
                          travelAgencyId,
                          authChk);
    }

    public static TripUser of(Long id,
                              String name,
                              String nickName,
                              String email,
                              String password,
                              String phoneNumber,
                              int point,
                              Gender gender,
                              boolean deleted,
                              Long profileId,
                              Boolean emailAuth,
                              String role,
                              Long travelAgencyId,
                              boolean authChk)
    {
        return new TripUser(id,
                name,
                nickName,
                email,
                password,
                phoneNumber,
                point,
                gender,
                deleted,
                profileId,
                emailAuth,
                role,
                travelAgencyId,
                authChk);
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
        if (!(o instanceof TripUser that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
