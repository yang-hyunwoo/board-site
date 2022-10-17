package com.board.boardsite.service.adm.auth;

import com.board.boardsite.domain.constant.Gender;
import com.board.boardsite.domain.travel.TravelAgencyList;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.response.adm.auth.AdmUserDto;
import com.board.boardsite.dto.user.AdmTripUserDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.user.TripUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdmAuthService {


    private final TripUserRepository tripUserRepository;



    @Transactional(readOnly = true)
    public PageImpl<AdmUserDto> userList(String searchKeyWord, Pageable pageable) {

        if (searchKeyWord == null || searchKeyWord.isBlank()) {
            return tripUserRepository.findUser("ADMIN",pageable);
        }else {
           return tripUserRepository.findUserEmailContaining("ADMIN", searchKeyWord, pageable);
        }
    }

    @Transactional
    public void acceptAdmin(Long adminId) {
        var tripUser = tripUserRepository.findByIdAndAuthChk(adminId,false).orElseThrow(()->new BoardSiteException(ErrorCode.USER_NOT_FOUND));
            tripUser.setAuthChk(!tripUser.isAuthChk());
            tripUserRepository.saveAndFlush(tripUser);
    }

    @Transactional
    public void reAcceptAdmin(Long adminId) {
        var tripUser = tripUserRepository.findByIdAndAuthChk(adminId,true).orElseThrow(()->new BoardSiteException(ErrorCode.USER_NOT_FOUND));
        tripUser.setAuthChk(!tripUser.isAuthChk());
        tripUserRepository.saveAndFlush(tripUser);
    }


}
