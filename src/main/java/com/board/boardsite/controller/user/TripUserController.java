package com.board.boardsite.controller.user;


import com.board.boardsite.dto.request.user.EmailAuthRequest;
import com.board.boardsite.dto.request.user.TripUserLoginRequest;
import com.board.boardsite.dto.request.user.TripUserUpdateRequest;
import com.board.boardsite.dto.response.user.TripUserLoginResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.dto.user.TripUserDto;
import com.board.boardsite.dto.request.user.TripUserJoinRequest;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.user.TripUserJoinResponse;
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

    @GetMapping("/my-page")
    public Response<TripUserDto> myPage(@AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
        var myPage = tripUserService.myPage(tripUserPrincipal.id());
        return Response.success(myPage);
    }

    @PostMapping("/change/password")
    public Response<Boolean> changePassword(@RequestBody TripUserLoginRequest request ,
                                            @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
        tripUserService.changePassword(tripUserPrincipal.id(),request.password());
        return Response.success(true);
    }

    @PostMapping("/change/other")
    public Response<Boolean> changeUserOther(@RequestBody TripUserUpdateRequest request ,
                                            @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
        System.out.println(":::::::::::::"+request);
        tripUserService.changeUserOther(tripUserPrincipal.id(),request.toDto());
        return Response.success(true);
    }

}
