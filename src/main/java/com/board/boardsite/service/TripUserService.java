package com.board.boardsite.service;

import com.board.boardsite.domain.TripUser;
import com.board.boardsite.dto.TripUserDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.TripUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TripUserService {

    private final TripUserRepository tripUserRepository;

    private final BCryptPasswordEncoder encoder;

    @Transactional
    public TripUserDto join(TripUserDto tripUserDto) {
        tripUserRepository.findByEmail(tripUserDto.email()).ifPresent(it -> {
            throw new BoardSiteException(ErrorCode.DUPLICATED_EMAIL, String.format("%s is duplicated", tripUserDto.email()));
        });

        String passwordEncode = encoder.encode(tripUserDto.password());


        TripUser tripUser = tripUserRepository.save(tripUserDto.toEntity(passwordEncode));


        return TripUserDto.from(tripUser);
    }

}