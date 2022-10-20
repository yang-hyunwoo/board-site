package com.board.boardsite.repository.user;

import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.repository.querydsl.user.AdmAuthCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TripUserRepository extends JpaRepository<TripUser, Long>, AdmAuthCustomRepository {

    Optional<TripUser> findByEmail(String email);

    Optional<TripUser> findByNickName(String nickName);

    Optional<TripUser> findByEmailAndEmailAuthAndDeleted(String email,boolean emailAuth,boolean deleted);

    Optional<TripUser> findByEmailAndEmailAuthAndDeletedAndAuthChk(String email,boolean emailAuth,boolean deleted,boolean authChk);



    Optional<TripUser> findByIdAndAuthChk(Long adminId , boolean authChk);



}
