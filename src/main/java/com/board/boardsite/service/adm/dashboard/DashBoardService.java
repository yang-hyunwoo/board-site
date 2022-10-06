package com.board.boardsite.service.adm.dashboard;

import com.board.boardsite.dto.response.adm.dashboard.TravelListCountDto;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.dto.travel.TravelAgencyListDto;
import com.board.boardsite.repository.travel.TravelAgencyListRepository;
import com.board.boardsite.repository.travel.TravelAgencyReservationRepository;
import com.querydsl.core.Tuple;
import com.sun.xml.bind.v2.TODO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashBoardService {

    private final TravelAgencyListRepository travelAgencyListRepository;

    private final TravelAgencyReservationRepository travelAgencyReservationRepository;

    @Transactional(readOnly = true)
    public List<TravelAgencyListDto> travelAgencyTripList(Collection<? extends GrantedAuthority> authorities, Long travelAgencyId) {

        /*TODO: 현재 날짜 상관없이 모든 여행 일정을 불러옴 -> (추가작업) 현재 월 (-1~+1) 달까지 조회 하도록 수정 하기

         */
        if(authorities.stream().toList().get(0).toString().equals(TripUserPrincipal.RoleType.ADMIN.toString())){
            return travelAgencyListRepository.findByTravelAgencyId(travelAgencyId).stream().map(TravelAgencyListDto::from).collect(Collectors.toList());
        }else {
            return travelAgencyListRepository.findAll().stream().map(TravelAgencyListDto::from).collect(Collectors.toList());
        }

       }

    @Transactional(readOnly = true)
    //TODO: 현재 날짜 조회는 count 된것만 조회 되는중 이 부분을 현재 월에 대해 모든  count 조회 하도록 수정 해보기
    public List<TravelListCountDto> travelAgencyTripPayCount(Long id , Collection<? extends GrantedAuthority> authorities) {
            var a = travelAgencyReservationRepository.findTravelAgencyReservation(id,authorities.stream().toList().get(0).toString());
            return a;


    }
}
