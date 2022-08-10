package com.board.boardsite.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Entity
public class TravelAgencyReservationList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Setter
    private TravelAgency travelAgency;

    @ManyToOne
    @Setter
    private TravelAgencyList travelAgencyList;

    @Setter
    @ManyToOne(optional = false)
    private TripUser tripUser; // 유저 정보 (ID)

    @Setter
    @Column(nullable = false)
    private int paid;

    @Setter
    @Column(nullable = false)
    private int salePercent;

    @Setter
    @Column(nullable = false , length = 1)
    private String useYn;

    protected TravelAgencyReservationList() {

    }

    private TravelAgencyReservationList( int paid, int salePercent, String useYn) {
        this.paid = paid;
        this.salePercent = salePercent;
        this.useYn = useYn;
    }

    public static TravelAgencyReservationList of(int paid, int salePercent, String useYn) {
        return new TravelAgencyReservationList(paid,salePercent,useYn);
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
