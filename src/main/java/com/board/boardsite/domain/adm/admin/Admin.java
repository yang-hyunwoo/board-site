package com.board.boardsite.domain.adm.admin;


import com.board.boardsite.domain.AuditingFields;
import com.board.boardsite.domain.constant.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Entity
@Table(indexes = {
        @Index(columnList = "nickname"),
        @Index(columnList = "email"),
        @Index(columnList = "name")
})
@Where(clause = "deleted = false")
public class Admin extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                        //ID

    @Setter
    @NotNull(message = "이름을 입력해주세요")
    @Column(nullable = false , length = 50)
    private String name;                                    //이름

    @Setter
    @NotNull(message = "닉네임을 입력해주세요")
    @Column(nullable = false , length = 50)
    private String nickName;                                    //이름

    @Setter
    @NotNull(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @Column(nullable = false , length = 100)
    private String email;                                   //이메일

    @Setter
    @NotNull(message = "패스워드를 입력해주세요.")
    @Size(min = 8 , message = "최소 8자리의 비밀번호가 필요합니다.")
    @Column(nullable = false , length = 1000)
    private String password;                                //패스워트


    @Setter
    @Column(nullable = false)
    private String role;                                    //권한

    @Setter
    @Column(nullable = false)
    private String travelAgencyId;                          //여행사Id
    @Setter
    @Column(nullable = false)
    private boolean deleted;

    private Boolean emailAuth;

    private Boolean authChk;                                 //승인 여부

    private String refreshToken;

    @Setter
    private Long profileId;

    protected Admin() {

    }

    private Admin(String name,
                  String nickName,
                  String email,
                  String password,
                  String role,
                  String travelAgencyId,
                  boolean deleted,
                  Long profileId,
                  Boolean emailAuth,
                  boolean authChk)
    {
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.role =role;
        this.travelAgencyId = travelAgencyId;
        this.deleted = deleted;
        this.emailAuth = emailAuth;
        this.authChk = authChk;
        this.profileId = profileId;
    }

    public Admin(Long id,
                 String name,
                 String nickName,
                 String email,
                 String password,
                 String role,
                 String travelAgencyId,
                 boolean deleted,
                 Long profileId,
                 Boolean emailAuth,
                 boolean authChk) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.role =role;
        this.travelAgencyId = travelAgencyId;
        this.deleted = deleted;
        this.profileId = profileId;
        this.emailAuth = emailAuth;
        this.authChk =authChk;
    }

    public static Admin of(String name,
                           String nickName,
                           String email,
                           String password,
                           String role,
                           String travelAgencyId,
                           boolean deleted,
                           Long profileId,
                           Boolean emailAuth,
                           boolean authChk)
    {
      return new Admin(name,
                          nickName,
                          email,
                          password,
                          role,
                          travelAgencyId,
                          deleted,
                          profileId,
                          emailAuth,
                          authChk);
    }

    public static Admin of(Long id,
                           String name,
                           String nickName,
                           String email,
                           String password,
                           String role,
                           String travelAgencyId,
                           boolean deleted,
                           Long profileId,
                           Boolean emailAuth,
                           boolean authChk)
    {
        return new Admin(id,
                name,
                nickName,
                email,
                password,
                role,
                travelAgencyId,
                deleted,
                profileId,
                emailAuth,
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
        if (!(o instanceof Admin that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
