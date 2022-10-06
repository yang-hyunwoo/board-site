package com.board.boardsite.service.adm.admin;

import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.dto.travel.TravelAgencyDto;
import com.board.boardsite.dto.user.EmailAuthDto;
import com.board.boardsite.dto.user.TripUserDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.travel.TravelAgencyListRepository;
import com.board.boardsite.repository.travel.TravelAgencyRepository;
import com.board.boardsite.repository.user.EmailAuthRepository;
import com.board.boardsite.repository.user.TripUserRepository;
import com.board.boardsite.service.user.EmailService;
import com.board.boardsite.support.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final TripUserRepository tripUserRepository;
    private final EmailAuthRepository emailAuthRepository;
    private final BCryptPasswordEncoder encoder;
    private final EmailService emailService;

    private final TravelAgencyRepository travelAgencyRepository;
    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;


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



    //관리자 회원 가입 시 여행사 리스트 조회
    @Transactional(readOnly = true)
    public List<TravelAgencyDto> travelAgencyList() {

//        return travelAgencyRepository.findAllByDeleted(false).map(TravelAgencyDto::from);
        return travelAgencyRepository.findAllByDeleted(false).stream().map(TravelAgencyDto::from).toList();
    }


    public String login(String email , String password) {
        var tripUser = tripUserRepository.findByEmailAndEmailAuthAndDeletedAndAuthChk(email,true,false,true).orElseThrow(() -> new BoardSiteException(ErrorCode.NOT_ALLOWED,String.format("%s not founded",email)));
        if(!encoder.matches(password , tripUser.getPassword())) {
            throw new BoardSiteException(ErrorCode.INVALID_PASSWORD);
        }
        if(tripUser.getRole().equals("USER") || tripUser.getRole().length()==0){
            throw new BoardSiteException(ErrorCode.NOT_PERMITTION);
        }

        String token = JwtTokenUtils.generateToken(email, secretKey, tripUser.getRole(),tripUser.getTravelAgencyId(),expiredTimeMs);
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
        tripUser.setProfileId(tripUserDto.profileId());
        tripUser.setNickName(tripUserDto.nickName());
        tripUser.setGender(tripUserDto.gender());
        tripUser.setPhoneNumber(tripUserDto.phoneNumber());
    }

}
