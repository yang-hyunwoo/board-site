package com.board.boardsite.domain.travel;


import com.board.boardsite.domain.AuditingFields;
import com.board.boardsite.domain.article.ArticleComment;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
        @Index(columnList = "readCount")
})
public class TravelAgencyList extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Setter
    private TravelAgency travelAgency; // 게시글 (ID)

    @ToString.Exclude
    @OneToMany(mappedBy = "travelAgencyList", cascade = CascadeType.ALL)
    private final Set<TravelAgencyReservation> travelAgencyReservationLists = new LinkedHashSet<>();      //여행사 여행 목록 ID


    @Setter
    @Column(nullable = false , length = 50)
    private String city;

    @Column(nullable = false , length = 8)
    private String travelStartAt; // 출발일시

    @Column(nullable = false, length = 8)
    private String travelEndAt   ; // 종료일시

    @Setter
    @Column(nullable = false , length = 100)
    private String title;                           //제목

    @Setter
    @Column(nullable = false , columnDefinition = "TEXT")
    private String content;                         //내용

    @Setter
    @Column(nullable = false)
    private int realPaid;                           //실제 비용

    @Setter
    private int salePercent;                        //세일 퍼센트

    @Setter
    private int salePaid;                           //세일후 비용

    @Setter
    private int personCount;                       //예약 인원

    @Setter
    @Column(nullable = false)
    private int personMaxCount;                     //예약 총 인원

    @Setter
    private int readCount;                          //조회수

    @Setter
    @Column(nullable = false)
    private boolean deleted;

    @Setter
    private Long thumnbnailFileId;                  //썸네일 파일 아이디

    @Setter
    private Integer sort;                           //사용자 메인 화면 보여줄 순서

    @ToString.Exclude
    @OneToMany(mappedBy = "travelAgencyList", cascade = CascadeType.ALL)
    private final Set<TravelAgencyLike> travelAgencyLikes = new LinkedHashSet<>();



    protected  TravelAgencyList() {

    }

    private TravelAgencyList(String city,
                            String travelStartAt,
                             String travelEndAt,
                            String title,
                            String content,
                            int realPaid,
                            int salePercent,
                            int salePaid,
                            int personCount,
                            int personMaxCount,
                            int readCount,
                            Long thumnbnailFileId,
                             Integer sort,
                             TravelAgency travelAgency)
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
        this.thumnbnailFileId = thumnbnailFileId;
        this.sort           = sort;
        this.travelAgency = travelAgency;
    }

    public static TravelAgencyList of(String city,
                                      String travelStartAt,
                                      String travelEndAt,
                                      String title,
                                      String content,
                                      int realPaid,
                                      int salePercent,
                                      int salePaid,
                                      int personCount,
                                      int personMaxCount,
                                      int readCount,
                                      Long thumnbnailFileId,
                                      Integer sort,
                                      TravelAgency travelAgency)
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
                    thumnbnailFileId,
                    sort,
                    travelAgency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TravelAgencyList that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void readCountPlus(int readCount){
        this.readCount = readCount+1;
    }

    public void personPlusCount(int personCount , int count){
        this.personCount = personCount+count;
    }
    public void personMinusCount(int personCount , int count){
        this.personCount = personCount-count;
    }
}
