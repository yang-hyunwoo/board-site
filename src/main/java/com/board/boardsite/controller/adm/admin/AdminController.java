package com.board.boardsite.controller.adm.admin;

import com.board.boardsite.dto.request.user.TripUserLoginRequest;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.travel.TravelAgencyResponse;
import com.board.boardsite.dto.response.user.TripUserLoginResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.adm.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/adm/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;


    @PostMapping("/login")
    public Response<TripUserLoginResponse> login(@RequestBody TripUserLoginRequest request) {
        String token = adminService.login(request.email(),request.password());
        return Response.success(new TripUserLoginResponse(token));
    }

    /*
       관리자 회원가입시 여행사 리스트 불러오기
     */
    @GetMapping("/list")
    public Response<List<TravelAgencyResponse>> travelAgencyList(){
        List<TravelAgencyResponse> travelAgencyResponses = adminService.travelAgencyList().stream().map(TravelAgencyResponse::from).collect(Collectors.toList());
        return Response.success(travelAgencyResponses);
    }


    @GetMapping("/auth")
    public Response<Map> auth(@AuthenticationPrincipal TripUserPrincipal tripUserPrincipal ){
        HashMap auth = new HashMap();
        auth.put("role",tripUserPrincipal.role());
        auth.put("agencyId",tripUserPrincipal.travelAgencyId());
        return Response.success(auth);
    }


}
