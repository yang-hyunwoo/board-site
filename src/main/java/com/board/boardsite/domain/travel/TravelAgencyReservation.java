package com.board.boardsite.domain.travel;

import com.board.boardsite.domain.AuditingFields;
import com.board.boardsite.domain.user.TripUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Entity
@Table(indexes = {
        @Index(columnList = "merchantUid"),
        @Index(columnList = "impUid"),
        @Index(columnList = "payName")
})
public class TravelAgencyReservation extends AuditingFields {

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
    @Column(nullable = false  , length = 255)
    private String merchantUid;

    @Setter
    @Column(nullable = false , length = 255)
    private String impUid;

    @Setter
    @Column(nullable = false , length = 100)
    private String payEmail;

    @Setter
    @Column(nullable = false , length = 50)
    private String payName;

    @Setter
    @Column
    private int paid;

    @Setter
    @Column
    private int realPaid;

    @Setter
    @Column
    private int personCount;

    @Setter
    @Column
    private int salePercent;

    @Setter
    @Column(length = 1000)
    private String reFundReason;

    @Setter
    @Column(length = 1000)
    private String failReason;


    @Setter
    @Column(nullable = false )
    private boolean deleted;                       //사용 여부



    protected TravelAgencyReservation() {

    }

    private TravelAgencyReservation( String merchantUid,
                                         String impUid,
                                         String payEmail,
                                         String payName,
                                         int paid,
                                         int realPaid,
                                         int personCount,
                                         int salePercent,
                                         boolean deleted,
                                         TripUser tripUser,
                                         TravelAgency travelAgency,
                                         TravelAgencyList travelAgencyList) {
        this.merchantUid = merchantUid;
        this.impUid = impUid;
        this.payEmail = payEmail;
        this.payName = payName;
        this.paid = paid;
        this.realPaid = realPaid;
        this.personCount = personCount;
        this.salePercent = salePercent;
        this.deleted = deleted;
        this.tripUser = tripUser;
        this.travelAgency = travelAgency;
        this.travelAgencyList = travelAgencyList;
    }

    public static TravelAgencyReservation of(String merchantUid,
                                                 String impUid,
                                                 String payEmail,
                                                 String payName,
                                                 int paid,
                                                 int realPaid,
                                                 int personCount,
                                                 int salePercent,
                                                 boolean deleted,
                                                 TripUser tripUser,
                                                 TravelAgency travelAgency ,
                                                 TravelAgencyList travelAgencyList) {
        return new TravelAgencyReservation(merchantUid,
                impUid,
                payEmail,
                payName,
                paid,
                realPaid,
                personCount,
                salePercent,
                deleted,
                tripUser,
                travelAgency,
                travelAgencyList);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TravelAgencyReservation that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
