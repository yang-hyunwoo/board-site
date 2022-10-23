package com.board.boardsite.controller.common;

import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.user.TripUserLoginResponse;
import com.board.boardsite.service.common.NaverService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/naver")
@RequiredArgsConstructor
public class NaverLoginController {

    private final NaverService naverService;

    @Value("${naver.client-id}")
    private String clientId;

    @Value("${naver.client-secret}")
    private String client_sercet;

    @Value("${naver.callback-url}")
    private String callbackUrl;

    @GetMapping("/naver-client")
    public Response<HashMap> naverClient() {

        HashMap<String, String> map = new HashMap<>();
        map.put("clientId", clientId);
        map.put("callbackUrl", callbackUrl);

        return Response.success(map);
    }


    @GetMapping("/callback")
    public Response<TripUserLoginResponse> naverCallbackUrl(@RequestParam String code, @RequestParam String state, @RequestParam(required = false) String refresh_token) throws ParseException {
        String token = naverService.naverService(code ,state,"authorization_code",clientId,client_sercet,refresh_token);
        return Response.success(new TripUserLoginResponse(token));

    }

}
