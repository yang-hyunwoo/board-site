package com.board.boardsite.controller.userController;


import com.board.boardsite.dto.request.user.EmailAuthRequest;
import com.board.boardsite.dto.request.user.TripUserLoginRequest;
import com.board.boardsite.dto.response.user.TripUserLoginResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.dto.user.TripUserDto;
import com.board.boardsite.dto.request.user.TripUserJoinRequest;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.user.TripUserJoinResponse;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.service.user.EmailService;
import com.board.boardsite.service.user.TripUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trip/users")
@RequiredArgsConstructor
@Slf4j
public class TripUserController {

    private final TripUserService tripUserService;

    private final EmailService emailService;

    @PostMapping("/join")
    public Response<TripUserJoinResponse> join(@RequestBody TripUserJoinRequest request) {
        TripUserDto tripUserDto  = tripUserService.join(request.toDto());
        return Response.success(TripUserJoinResponse.from(tripUserDto));
    }



    @GetMapping("/confirm-email")
    public Response<String> confirmEmail(@ModelAttribute EmailAuthRequest request) {

           boolean test =  emailService.confirmEmail(request);

           if(test){
//               return Response.success("인증이 완료되었습니다.");
           } else {
               emailService.retryConfirmEmail(request);
//               return null;
           }
        return Response.success("인증이 완료되었습니다.");
    }

    @PostMapping("/login")
    public Response<TripUserLoginResponse> login(@RequestBody TripUserLoginRequest request) {
        String token = tripUserService.login(request.email(),request.password());
        return Response.success(new TripUserLoginResponse(token));

    }

    @GetMapping("/aaaa")
    public String  aaa(@AuthenticationPrincipal TripUserPrincipal tripUserPrincipal){
//        System.out.println(tripUserPrincipal.email());
//        System.out.println(tripUserPrincipal.name());
        return "aa";

    }


}
