package com.board.boardsite.domain.travel;

import com.board.boardsite.domain.AuditingFields;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

@Getter
@Entity
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "name")
})
public class TravelAgency extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                        //ID

    @Setter
    @NotEmpty(message = "여행사 명을 입력하세요.")
    @Column(nullable = false , length = 50)
    private String name;                                    //여행사 이름

    @Setter
    @NotEmpty(message = "주소를 입력해주세요.")
    @Column(nullable = false , length = 1000)
    private String address;                                 //여행사 주소

    @Setter
    @NotEmpty(message = "번호를 입력해주세요.")
    @Column(nullable = false , length = 50)
    private String tel;                                     //여행사 전화번호

    @Setter
    @Column(nullable = false, columnDefinition = "TEXT")
    private String detail;                                  //여행사 상세 정보

    @Setter                                                 //여행사 파일 순번
    private Long fileId;

    @Setter
    private String comment;                                 //여행사 설명 간단

    @Setter
    @Column(nullable = false )
    private boolean deleted;                                   //여행사 사용 여부

    @ToString.Exclude
    @OneToMany(mappedBy = "travelAgency", cascade = CascadeType.ALL)
//    @Where(clause = "deleted = false")
    private final List<TravelAgencyList> travelAgencyLists = new ArrayList<>();   //여행사 여행 목록 ID

    @ToString.Exclude
    @OneToMany(mappedBy = "travelAgency", cascade = CascadeType.ALL)
    private final Set<TravelAgencyReservation> travelAgencyReservationLists = new LinkedHashSet<>();      //여행사 여행 목록 ID


    protected TravelAgency() {

    }

    private TravelAgency ( String name, String address, String tel, String detail,String comment, Long fileId , boolean deleted) {
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.detail = detail;
        this.comment = comment;
        this.fileId = fileId;
        this.deleted = deleted;
    }

    public static TravelAgency of( String name,
                                   String address,
                                   String tel,
                                   String detail,
                                   String comment,
                                   Long fileId ,
                                   boolean deleted) {
        return new TravelAgency(name,
                address,
                tel,
                detail,
                comment,
                fileId,
                deleted);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TravelAgency that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
