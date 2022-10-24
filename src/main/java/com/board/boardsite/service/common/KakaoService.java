package com.board.boardsite.service.common;

import com.board.boardsite.domain.constant.Gender;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.user.TripUserDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.user.TripUserRepository;
import com.board.boardsite.service.user.TripUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {

    private static WebClient webClient = WebClient.create();
    private final TripUserRepository tripUserRepository;


    private final TripUserService tripUserService;

    public String kakaoService(String code ,
                             String resApiKey ,
                             String redirect_uri,
                             String adminKey) throws ParseException {
        final String uri = UriComponentsBuilder.fromUriString("https://kauth.kakao.com")
                .path("/oauth/token")
                .queryParam("grant_type","authorization_code")
                .queryParam("client_id",resApiKey)
                .queryParam("code",code)
                .queryParam("redirect_uri",redirect_uri)
                .build()
                .encode()
                .toUriString();


        var kakaoId =  webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(JSONObject.class)
                .block();

        return kakaoUserChk(kakaoId.get("access_token"));


    }

    public String kakaoUserChk(Object access_token) throws ParseException {
        final String profileUri = UriComponentsBuilder
                .fromUriString("https://kapi.kakao.com")
                .path("/v2/user/me")
                .build()
                .encode()
                .toUriString();
        var kakaoInfo = webClient
                .get()
                .uri(profileUri)
                .header("Authorization", "Bearer "+ access_token)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info("kakaoInfo : {}" , kakaoInfo);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(kakaoInfo);
        JSONObject jsonObject1 = (JSONObject) jsonObject.get("kakao_account");
        JSONObject jsonObject2 = (JSONObject) jsonObject1.get("profile");
        log.info("naverInfo : {}" , jsonObject2.get("nickname").toString());

        if(!join(jsonObject1.get("email").toString())){
            TripUserDto tripUserDto = TripUserDto.of(jsonObject1.get("email").toString(),
                    jsonObject2.get("nickname").toString(),
                    jsonObject2.get("nickname").toString(),
                    "010-9999-9999",
                    jsonObject1.get("gender").toString()=="mail"? Gender.M:Gender.F,
                    "USER",
                    "KAKAO");
            System.out.println("tripUserDto:::"+tripUserDto);
            tripUserService.join(tripUserDto);

        }
        return tripUserService.snsLogin(jsonObject1.get("email").toString(),"KAKAO");

    }

    public boolean join(String email) {
      return tripUserRepository.findByEmail(email.trim()).isPresent();
    }
}
