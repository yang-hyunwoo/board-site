package com.board.boardsite.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Entity
public class TravelAgencyReservationList extends AuditingFields {

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
    private int paid;                           //비용

    @Setter
    @Column(nullable = false)
    private int salePercent;                    //세일 비용

    @Setter
    @Column(nullable = false , length = 1)
    private String useYn;                       //사용 여부

    protected TravelAgencyReservationList() {

    }

    private TravelAgencyReservationList( int paid,
                                         int salePercent,
                                         String useYn,
                                         TripUser tripUser,
                                         TravelAgency travelAgency,
                                         TravelAgencyList travelAgencyList) {
        this.paid = paid;
        this.salePercent = salePercent;
        this.useYn = useYn;
        this.tripUser = tripUser;
        this.travelAgency = travelAgency;
        this.travelAgencyList =travelAgencyList;
    }

    public static TravelAgencyReservationList of(int paid,
                                                 int salePercent,
                                                 String useYn,
                                                 TripUser tripUser,
                                                 TravelAgency travelAgency ,
                                                 TravelAgencyList travelAgencyList) {
        return new TravelAgencyReservationList(paid,salePercent,useYn,tripUser,travelAgency,travelAgencyList);
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
