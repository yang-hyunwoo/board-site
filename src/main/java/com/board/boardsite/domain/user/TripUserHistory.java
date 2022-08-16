package com.board.boardsite.domain.user;


import com.board.boardsite.domain.AuditingFields;
import com.board.boardsite.domain.article.ArticleComment;
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
public class TripUserHistory extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                        //ID

    @ManyToOne
    @ToString.Exclude
    private TripUser tripUser;

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

    private int point;                                      //포인트

    @Enumerated(value=EnumType.STRING)
    private Gender gender;                                  //성별


    protected TripUserHistory() {

    }
    private TripUserHistory(Long id , String name, String nickName, String email, String password, int point, Gender gender) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.point = point;
        this.gender = gender;
    }

    public static TripUserHistory of(Long id , String name, String nickName, String email, String password, int point, Gender gender) {
        return new TripUserHistory(id,name,nickName,email,password,point,gender);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TripUserHistory that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
