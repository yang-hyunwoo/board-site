package com.board.boardsite.service.user;

import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.request.user.TripUserJoinRequest;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.dto.user.EmailAuthDto;
import com.board.boardsite.dto.user.TripUserDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.user.TripUserRepository;
import com.board.boardsite.repository.user.EmailAuthRepository;
import com.board.boardsite.support.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;

    public TripUserPrincipal loadUserByEmail(String email) {
        return  tripUserRepository.findByEmail(email)
                .map(TripUserDto::from)
                .map(TripUserPrincipal::from)
                .orElseThrow(() -> new BoardSiteException(ErrorCode.USER_NOT_FOUND,String.format("%s not founded",email)));

    }

    @Transactional
    public TripUserDto join(TripUserDto tripUserDto) {
        tripUserRepository.findByEmail(tripUserDto.email().trim()).ifPresent(it -> {
            throw new BoardSiteException(ErrorCode.DUPLICATED_EMAIL, String.format("%s is duplicated", tripUserDto.email()));
        });

        String passwordEncode = encoder.encode(tripUserDto.password());
        TripUser tripUser = tripUserRepository.save(tripUserDto.toEntity(passwordEncode));
        EmailAuthDto emailAuthDto = EmailAuthDto.of(tripUserDto.email(), UUID.randomUUID().toString(),false, LocalDateTime.now());
        emailAuthRepository.save(emailAuthDto.toEntity());
        emailService.send(emailAuthDto.email(),emailAuthDto.authToken());


        return TripUserDto.from(tripUser);
    }

    public String login(String email , String password) {
        var tripUser = tripUserRepository.findByEmailAndEmailAuthAndDeleted(email,true,false).orElseThrow(() -> new BoardSiteException(ErrorCode.EMAIL_NOT_FOUND,String.format("%s not founded",email)));
        if(!encoder.matches(password , tripUser.getPassword())) {
            throw new BoardSiteException(ErrorCode.INVALID_PASSWORD);
        }
        String token = JwtTokenUtils.generateToken(email,secretKey , expiredTimeMs);

        return token;
    }

    @Transactional(readOnly = true)
    public TripUserDto myPage(Long tripUserId) {
        var tripUser = tripUserRepository.findById(tripUserId).orElseThrow(()->new BoardSiteException(ErrorCode.USER_NOT_FOUND));

        return TripUserDto.from(tripUser);
    }

    @Transactional
    public void changePassword(Long tripUserId , String password) {
        var tripUser = tripUserRepository.findById(tripUserId).orElseThrow(()->new BoardSiteException(ErrorCode.USER_NOT_FOUND));
        String passwordEncode = encoder.encode(password);
        tripUser.setPassword(passwordEncode);
    }

    @Transactional
    public void changeUserOther(Long tripUserId , TripUserDto tripUserDto) {
        var tripUser = tripUserRepository.findById(tripUserId).orElseThrow(()->new BoardSiteException(ErrorCode.USER_NOT_FOUND));
        System.out.println(":::::::::::::"+tripUserDto.nickName());
        tripUser.setProfileId(tripUserDto.profileId());
        tripUser.setNickName(tripUserDto.nickName());
        tripUser.setGender(tripUserDto.gender());
        tripUser.setPhoneNumber(tripUserDto.phoneNumber());
    }

}