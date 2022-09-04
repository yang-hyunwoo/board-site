package com.board.boardsite.domain.tour;


import com.board.boardsite.domain.AuditingFields;
import com.board.boardsite.domain.article.ArticleComment;
import com.board.boardsite.domain.user.TripUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "city"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class Tour extends AuditingFields {

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

    @Setter
    private Long thumbnailId;

    @Setter
    @Column(nullable = false)
    private String city;

    @ToString.Exclude
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL)
    private final Set<TourComment> tourComments = new LinkedHashSet<>();

    protected Tour() {}



    private Tour(String title, String content , boolean deleted , int readCount ,String city, Long thumbnailId, TripUser tripUser) {
        this.title = title;
        this.content = content;
        this.deleted = deleted;
        this.tripUser = tripUser;
        this.readCount = readCount;
        this.city       = city;
        this.thumbnailId = thumbnailId;
    }

    public static Tour of(String title,
                          String content ,
                          boolean deleted ,
                          int readCount ,
                          String city,
                          Long thumbnailId ,
                          TripUser tripUser) {
        return new Tour(title,
                content,
                deleted,
                readCount,
                city,
                thumbnailId,
                tripUser);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tour that)) return false;
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

