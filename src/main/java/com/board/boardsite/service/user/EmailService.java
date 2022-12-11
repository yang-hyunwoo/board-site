package com.board.boardsite.service.user;

import com.board.boardsite.domain.user.EmailAuth;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.request.user.EmailAuthRequest;
import com.board.boardsite.repository.user.TripUserRepository;
import com.board.boardsite.repository.user.EmailAuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@EnableAsync
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;

    private final TripUserRepository tripUserRepository;

    private final EmailAuthRepository emailAuthRepository;

    private static final Long MAX_EXPIRE_TIME = 5L;
    @Async
    public void send(String email , String authToken){
        SimpleMailMessage sim = new SimpleMailMessage();
        sim.setTo(email);
        sim.setSubject("회원가입 이메일 인증");
//        sim.setText("http://localhost:8081/api/trip/users/confirm-email?email="+email+"&authToken="+authToken);
        sim.setText("https://board-site-production.up.railway.app/api/trip/users/confirm-email?email="+email+"&authToken="+authToken);

        javaMailSender.send(sim);
    }

    @Transactional
    public boolean confirmEmail(EmailAuthRequest request) {
        if(emailAuthRepository.findValidAuthByEmail(request.email(), request.authToken(), LocalDateTime.now()).isEmpty()){
            if(tripUserRepository.findByEmail(request.email()).isEmpty()) {
//                throw new BoardSiteException(ErrorCode.DUPLICATED_EMAIL, String.format("%s is duplicated", request.email()));
                return  false;
            }
//                throw new BoardSiteException(ErrorCode.EMAIL_TIME_INVAILED);
            return  false;
        }


        EmailAuth emailAuth = emailAuthRepository.findValidAuthByEmail(request.email(), request.authToken(), LocalDateTime.now()).get();
        TripUser tripUser = tripUserRepository.findByEmailAndLoginTypeIsNull(request.email()).get();
        emailAuth.useToken();
        tripUser.emailVerifiedSuccess();
        return true;

    }

    @Transactional
    public void retryConfirmEmail(EmailAuthRequest request) {

        EmailAuth emailAuth = emailAuthRepository.findByEmail(request.email()).get();
        emailAuth.setAuthToken(UUID.randomUUID().toString());
        emailAuth.setExpireDate(LocalDateTime.now().plusMinutes(MAX_EXPIRE_TIME));

        emailAuthRepository.saveAndFlush(emailAuth);
        send(request.email(),emailAuth.getAuthToken());
    }
}
