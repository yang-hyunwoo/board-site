package com.board.boardsite.controller.adm.admin;

import com.board.boardsite.dto.request.user.TripUserLoginRequest;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.user.TripUserLoginResponse;
import com.board.boardsite.service.adm.admin.AdminService;
import com.board.boardsite.service.user.TripUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @PostMapping("/join")
//    public Response<>

}
