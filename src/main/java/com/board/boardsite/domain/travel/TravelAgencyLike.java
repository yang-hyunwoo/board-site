package com.board.boardsite.domain.travel;

import com.board.boardsite.domain.AuditingFields;
import com.board.boardsite.domain.article.Article;
import com.board.boardsite.domain.article.ArticleComment;
import com.board.boardsite.domain.user.TripUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Entity
@Where(clause = "deleted = false")
public class TravelAgencyLike extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(optional = false)
    private TripUser tripUser; // 유저 정보 (ID)

    @Setter
    @ManyToOne(optional = false)
    private TravelAgencyList travelAgencyList;

    @Setter
    @Column(nullable = false)
    private boolean deleted;

    protected TravelAgencyLike() {}

    private TravelAgencyLike(TripUser tripUser , TravelAgencyList travelAgencyList , boolean deleted){
        this.tripUser = tripUser;
        this.travelAgencyList = travelAgencyList;
        this.deleted = deleted;
    }

    public static TravelAgencyLike of(TripUser tripUser , TravelAgencyList travelAgencyList , boolean deleted) {
        return new TravelAgencyLike(tripUser,travelAgencyList,deleted);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TravelAgencyLike that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
