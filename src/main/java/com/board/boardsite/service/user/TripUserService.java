package com.board.boardsite.service.user;

import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.user.EmailAuthDto;
import com.board.boardsite.dto.user.TripUserDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.user.TripUserRepository;
import com.board.boardsite.repository.user.EmailAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TripUserService {

    private final TripUserRepository tripUserRepository;

    private final EmailAuthRepository emailAuthRepository;
    private final BCryptPasswordEncoder encoder;

    private final EmailService emailService;

    @Transactional
    public TripUserDto join(TripUserDto tripUserDto) {
        tripUserRepository.findByEmail(tripUserDto.email()).ifPresent(it -> {
            throw new BoardSiteException(ErrorCode.DUPLICATED_EMAIL, String.format("%s is duplicated", tripUserDto.email()));
        });

        String passwordEncode = encoder.encode(tripUserDto.password());
        TripUser tripUser = tripUserRepository.save(tripUserDto.toEntity(passwordEncode));

        EmailAuthDto emailAuthDto = EmailAuthDto.of(tripUserDto.email(), UUID.randomUUID().toString(),false, LocalDateTime.now());
        emailAuthRepository.save(emailAuthDto.toEntity());
        emailService.send(emailAuthDto.email(),emailAuthDto.authToken());


        return TripUserDto.from(tripUser);
    }

}