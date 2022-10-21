package com.board.boardsite.repository.adm.user;

import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.repository.querydsl.user.AdmUserCustomRepository;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmUserRepository extends JpaRepository<TripUser , Long> , AdmUserCustomRepository {

    PageImpl<TripUser> findByRole(String role, Pageable pageable);

    PageImpl<TripUser> findByNameContainingAndRole(String input, String role, Pageable pageable);
    PageImpl<TripUser> findByEmailContainingAndRole(String input, String role, Pageable pageable);
    PageImpl<TripUser> findByNickNameContainingAndRole(String input, String role, Pageable pageable);


}
