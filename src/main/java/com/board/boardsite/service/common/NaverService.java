package com.board.boardsite.service.common;

import com.board.boardsite.domain.constant.Gender;
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
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
@Slf4j
@Service
@RequiredArgsConstructor
public class NaverService {

    private static WebClient webClient = WebClient.create();
    private final TripUserRepository tripUserRepository;

    private final TripUserService tripUserService;
    public String naverService(String code ,
                             String state,
                             String grant_type,
                             String client_id ,
                             String client_secret,
                             String refresh_token) throws ParseException {
        final String uri = UriComponentsBuilder.fromUriString("https://nid.naver.com")
                .path("/oauth2.0/token")
                .queryParam("grant_type",grant_type)
                .queryParam("client_id",client_id)
                .queryParam("client_secret",client_secret)
                .queryParam("code",code)
                .queryParam("state",state)
                .queryParam("refresh_token",refresh_token)
                .build()
                .encode()
                .toUriString();


        var naverId =  webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(JSONObject.class)
                .block();

        return naverUserChk(naverId.get("access_token"));



    }

    public String naverUserChk(Object access_token) throws ParseException {
        final String profileUri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/nid/me")
                .build()
                .encode()
                .toUriString();
        var naverInfo = webClient
                .get()
                .uri(profileUri)
                .header("Authorization", "Bearer "+ access_token)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(naverInfo);
        JSONObject jsonObject1 = (JSONObject) jsonObject.get("response");
        log.info("naverInfo : {}" , jsonObject1.get("nickname"));
        log.info("naverInfo : {}" , jsonObject1);


        if(!join(jsonObject1.get("email").toString())){
            TripUserDto tripUserDto = TripUserDto.of(jsonObject1.get("email").toString(),
                    jsonObject1.get("name").toString(),
                    jsonObject1.get("nickname").toString(),
                    jsonObject1.get("mobile").toString(),
                    jsonObject1.get("gender").toString()=="M"? Gender.M:Gender.F,
                    "USER",
                    "NAVER");

            tripUserService.join(tripUserDto);
        }

        return tripUserService.NaverLogin(jsonObject1.get("email").toString());

    }

    public boolean join(String email) {
      return tripUserRepository.findByEmail(email.trim()).isPresent();
    }
}
