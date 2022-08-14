package com.board.boardsite.domain;


import com.board.boardsite.domain.user.TripUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class ArticleComment  extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(optional = false)
    private Article article; // 게시글 (ID)

    @Setter
    @ManyToOne(optional = false)
    private TripUser tripUser; // 유저 정보 (ID)


    @Setter
    @Column(nullable = false, length = 4000)
    private String content; // 본문

    @Setter
    @Column(nullable = false, length = 1)
    private String useYn;



    protected ArticleComment() {}

    private ArticleComment(Article article, String content,String useYn , TripUser tripUser) {
        this.article = article;
        this.content = content;
        this.useYn = useYn;
        this.tripUser = tripUser;
    }

    public static ArticleComment of(Article article,  String content,String useYn , TripUser tripUser) {
        return new ArticleComment(article, content,useYn,tripUser);
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
