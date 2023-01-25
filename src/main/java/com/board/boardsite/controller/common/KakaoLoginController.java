package com.board.boardsite.controller.common;

import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.user.TripUserLoginResponse;
import com.board.boardsite.service.common.KakaoService;
import com.board.boardsite.service.common.NaverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@Api(tags ={"카카오 로그인 관련 Controller"})
@RestController
@RequestMapping("/api/kakao")
@RequiredArgsConstructor
public class KakaoLoginController {

    private final KakaoService kakaoService;

    @Value("${kakao.rest-api-key}")
    private String resApiKey;

    @Value("${kakao.callback-url}")
    private String callbackUrl;

    @Value("${kakao.admin-key}")
    private String adminKey;

    @GetMapping("/kakao-client")
    @ApiOperation(value = "카카오 로그인", notes = "카카오 관련 로그인")
    public Response<HashMap> kakaoClient() {

        HashMap<String, String> map = new HashMap<>();
        map.put("clientId", resApiKey);
        map.put("callbackUrl", callbackUrl);

        return Response.success(map);
    }


    @GetMapping("/callback")
    @ApiOperation(value = "카카오 로그인 후 콜백", notes = "카카오 관련 로그인후 콜백 ")
    public Response<TripUserLoginResponse> kakaoCallbackUrl(@RequestParam String code) throws ParseException {
        String token = kakaoService.kakaoService(code, resApiKey, callbackUrl,adminKey);
        return Response.success(new TripUserLoginResponse(token));

    }

}
