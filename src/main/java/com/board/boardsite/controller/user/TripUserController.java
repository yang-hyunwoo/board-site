package com.board.boardsite.controller.user;


import com.board.boardsite.dto.request.user.*;
import com.board.boardsite.dto.response.user.TripUserLoginResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.dto.user.TripUserDto;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.user.TripUserJoinResponse;
import com.board.boardsite.service.user.EmailService;
import com.board.boardsite.service.user.TripUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags ={"사용자 로그인 정보 제공하는 Controller"})
@RestController
@RequestMapping("/api/trip/users")
@RequiredArgsConstructor
@Slf4j
public class TripUserController {

    private final TripUserService tripUserService;

    private final EmailService emailService;

    /*
      사용자 / 관리자 회원가입 로직 동일
     */
    @PostMapping("/join")
    @ApiOperation(value = "회원 가입", notes = "관리자/사용자 회원가입을 한다.")
    public Response<TripUserJoinResponse> join(@Valid @RequestBody TripUserJoinRequest request) {
        TripUserDto tripUserDto  = tripUserService.join(request.toDto());
        return Response.success(TripUserJoinResponse.from(tripUserDto));
    }



    @GetMapping("/confirm-email")
    @ApiOperation(value = "이메일 인증", notes = "관리자/사용자 이메일 인증을 한다.")
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

    /**
     * 로그인
     * @param request
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "관리자/사용자 로그인 한다.")
    public Response<TripUserLoginResponse> login(@RequestBody TripUserLoginRequest request) {
        String token = tripUserService.login(request.email(),request.password());
        return Response.success(new TripUserLoginResponse(token));

    }

    /**
     * 23.01.29 비밀번호 찾기 (추가)
     * @param request
     * @return
     */
    @PostMapping("/pw-find")
    @ApiOperation(value = "비밀번호 찾기", notes = "사용자의 비밀번호를 random으로 변경 후 이메일 전송을 한다.")
    public Response<Boolean> pwFind(@RequestBody TripUserPasswordFindRequest request) {
        tripUserService.pwFind(request.name(), request.email());

        return Response.success(true);

    }

    /**
     * 마이페이지 데이터 조회
     * @param tripUserPrincipal
     * @return
     */
    @GetMapping("/my-page")
    @ApiOperation(value = "마이 페이지", notes = "마이페이지의 사용자 정보를 조회한다.")
    public Response<TripUserDto> myPage(@AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
        var myPage = tripUserService.myPage(tripUserPrincipal.id());
        return Response.success(myPage);
    }

    /**
     * 패스워드 변경
     * @param request
     * @param tripUserPrincipal
     * @return
     */
    @PostMapping("/change/password")
    @ApiOperation(value = "비밀번호 변경", notes = "마이페이지의 사용자 비밀번호를 변경한다.")
    public Response<Boolean> changePassword(@RequestBody TripUserLoginRequest request ,
                                            @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
        tripUserService.changePassword(tripUserPrincipal.id(),request.password());
        return Response.success(true);
    }

    /**
     * 패스워드 제외 정보 수정
     * @param request
     * @param tripUserPrincipal
     * @return
     */
    @PostMapping("/change/other")
    @ApiOperation(value = "비밀번호 제외 변경", notes = "마이페이지의 사용자 비밀번호 제외한 정보를 변경한다.")
    public Response<Boolean> changeUserOther(@RequestBody TripUserUpdateRequest request ,
                                            @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
        tripUserService.changeUserOther(tripUserPrincipal.id(),request.toDto());
        return Response.success(true);
    }

}
