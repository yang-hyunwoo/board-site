package com.board.boardsite.domain.article;


import com.board.boardsite.domain.AuditingFields;
import com.board.boardsite.domain.user.TripUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
@Where(clause = "deleted = false")
public class Article extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(optional = false)
    private TripUser tripUser; // 유저 정보 (ID)


    @Setter
    @Column(nullable = false , length = 100)
    private String title; // 제목

    @Setter
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // 본문

    @Setter
    @Column(nullable = false)
    private boolean deleted;

    @Setter
    @Column(nullable = false)
    private int readCount;

    @ToString.Exclude
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    protected Article() {}



    private Article(String title, String content , boolean deleted , int readCount , TripUser tripUser) {
        this.title = title;
        this.content = content;
        this.deleted = deleted;
        this.tripUser = tripUser;
        this.readCount = readCount;
    }

    public static Article of(String title, String content ,boolean deleted ,int readCount,TripUser tripUser) {
        return new Article(title, content,deleted, readCount,tripUser);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void readCountPlus(int readCount){
        this.readCount = readCount+1;
    }
}

