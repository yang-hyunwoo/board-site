package com.board.boardsite.repository.querydsl.user;

import com.board.boardsite.dto.response.adm.auth.AdmUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface AdmAuthCustomRepository {

    PageImpl<AdmUserDto> findUser(String auth , Pageable pageable);

    PageImpl<AdmUserDto> findUserEmailContaining(String auth ,String email, Pageable pageable);

}
