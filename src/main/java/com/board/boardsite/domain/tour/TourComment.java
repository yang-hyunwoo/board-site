package com.board.boardsite.domain.tour;


import com.board.boardsite.domain.AuditingFields;
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
public class TourComment extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(optional = false)
    private Tour tour; // 게시글 (ID)

    @Setter
    @ManyToOne(optional = false)
    private TripUser tripUser; // 유저 정보 (ID)


    @Setter
    @Column(nullable = false, length = 4000)
    private String content; // 본문

    @Setter
    @Column(nullable = false)
    private boolean deleted;



    protected TourComment() {}

    private TourComment(Tour tour, String content, boolean deleted , TripUser tripUser) {
        this.tour = tour;
        this.content = content;
        this.deleted = deleted;
        this.tripUser = tripUser;
    }

    public static TourComment of(Tour tour, String content, boolean deleted , TripUser tripUser) {
        return new TourComment(tour, content,deleted,tripUser);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TourComment that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
