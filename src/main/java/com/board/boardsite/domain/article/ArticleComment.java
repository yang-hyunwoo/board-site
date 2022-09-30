package com.board.boardsite.domain.article;


import com.board.boardsite.domain.AuditingFields;
import com.board.boardsite.domain.user.TripUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class ArticleComment extends AuditingFields {

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
    @NotNull(message = "댓글을 입력해주세요")
    @Column(nullable = false, length = 4000)
    private String content; // 본문

    @Setter
    @Column(nullable = false)
    private boolean deleted;



    protected ArticleComment() {}

    private ArticleComment(Article article, String content, boolean deleted , TripUser tripUser) {
        this.article = article;
        this.content = content;
        this.deleted = deleted;
        this.tripUser = tripUser;
    }

    public static ArticleComment of(Article article,  String content,boolean deleted , TripUser tripUser) {
        return new ArticleComment(article, content,deleted,tripUser);
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
