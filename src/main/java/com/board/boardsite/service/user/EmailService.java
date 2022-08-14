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

@Service
@EnableAsync
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;

    private final TripUserRepository tripUserRepository;

    private final EmailAuthRepository emailAuthRepository;

    @Async
    public void send(String email , String authToken){
        SimpleMailMessage sim = new SimpleMailMessage();
        sim.setTo(email);
        sim.setSubject("회원가입 이메일 인증");
        sim.setText("http://localhost:8081/api/trip/users/confirm-email?email="+email+"&authToken="+authToken);

        javaMailSender.send(sim);
    }

    @Transactional
    public void confirmEmail(EmailAuthRequest request){
        log.warn("request.email() :{}",request.email());
        log.warn("request.authToken() :{}",request.authToken());
        EmailAuth emailAuth = emailAuthRepository.findValidAuthByEmail(request.email(), request.authToken(), LocalDateTime.now()).get();
        TripUser tripUser = tripUserRepository.findByEmail(request.email()).get();
        emailAuth.useToken();
        tripUser.emailVerifiedSuccess();
    }

}
