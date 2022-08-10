package com.board.boardsite.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Entity
@Table(indexes = {
        @Index(columnList = "city"),
        @Index(columnList = "travelStartAt"),
        @Index(columnList = "travelEndAt"),
        @Index(columnList = "readCount"),
        @Index(columnList = "likeCount"),
})
public class TravelAgencyList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Setter
    private TravelAgency travelAgency; // 게시글 (ID)

    @ToString.Exclude
    @OneToMany(mappedBy = "travelAgencyList", cascade = CascadeType.ALL)
    private final Set<TravelAgencyReservationList> travelAgencyReservationLists = new LinkedHashSet<>();      //여행사 여행 목록 ID


    @Setter
    @Column(nullable = false , length = 50)
    private String city;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(nullable = false)
    private LocalDateTime travelStartAt; // 수정일시

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(nullable = false)
    private LocalDateTime travelEndAt   ; // 수정일시

    @Setter
    @Column(nullable = false , length = 100)
    private String title;

    @Setter
    @Column(nullable = false , columnDefinition = "TEXT")
    private String content;

    @Setter
    @Column(nullable = false)
    private int realPaid;

    @Setter
    private int salePercent;

    @Setter
    private int salePaid;

    @Setter
    private int personCount;

    @Setter
    @Column(nullable = false)
    private int personMaxCount;

    @Setter
    private int readCount;

    @Setter
    private int likeCount;


    protected  TravelAgencyList() {

    }

    private TravelAgencyList(String city,
                            LocalDateTime travelStartAt,
                            LocalDateTime travelEndAt,
                            String title,
                            String content,
                            int realPaid,
                            int salePercent,
                            int salePaid,
                            int personCount,
                            int personMaxCount,
                            int readCount,
                            int likeCount)
    {
        this.city = city;
        this.travelStartAt = travelStartAt;
        this.travelEndAt = travelEndAt;
        this.title = title;
        this.content = content;
        this.realPaid = realPaid;
        this.salePercent = salePercent;
        this.salePaid = salePaid;
        this.personCount = personCount;
        this.personMaxCount = personMaxCount;
        this.readCount = readCount;
        this.likeCount = likeCount;
    }

    public static TravelAgencyList of(String city,
                               LocalDateTime travelStartAt,
                               LocalDateTime travelEndAt,
                               String title,
                               String content,
                               int realPaid,
                               int salePercent,
                               int salePaid,
                               int personCount,
                               int personMaxCount,
                               int readCount,
                               int likeCount)
    {
        return new TravelAgencyList(city,
                    travelStartAt,
                    travelEndAt,
                    title,
                    content,
                    realPaid,
                    salePercent,
                    salePaid,
                    personCount,
                    personMaxCount,
                    readCount,
                    likeCount);
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
