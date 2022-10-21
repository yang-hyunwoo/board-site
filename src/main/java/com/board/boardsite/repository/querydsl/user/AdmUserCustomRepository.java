package com.board.boardsite.repository.querydsl.user;

import com.board.boardsite.dto.response.adm.auth.AdmUserDto;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface AdmUserCustomRepository {

    PageImpl<AdmUserDto> findTravelAgencyIdAndUser(Long travelAgencyId , Pageable pageable);

    PageImpl<AdmUserDto> findTravelAgencyIdAndUserAndName(Long travelAgencyId, String name, Pageable pageable);

    PageImpl<AdmUserDto> findTravelAgencyIdAndUserAndEmail(Long travelAgencyId, String email, Pageable pageable);

    PageImpl<AdmUserDto> findTravelAgencyIdAndUserAndNickName(Long travelAgencyId, String nickName, Pageable pageable);


}
