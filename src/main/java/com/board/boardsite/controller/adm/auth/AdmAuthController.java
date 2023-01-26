package com.board.boardsite.controller.adm.auth;


import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.adm.auth.UserResponse;
import com.board.boardsite.service.adm.auth.AdmAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

@Api(tags ={"관리자 인증 조회 Controller"})
@RestController
@RequestMapping("/api/adm/auth")
@RequiredArgsConstructor
public class AdmAuthController {

    private final AdmAuthService admAuthService;

    /**
     * 관리자 리스트 조회
     * @param searchKeyWord
     * @param pageable
     * @return
     */
    @GetMapping("")
    @ApiOperation(value = "관리자 목록 조회", notes = "관리자의 목록을 조회 한다.")
    public Response<Page<UserResponse>> userList(@RequestParam(required = false) String searchKeyWord,
                                                     @PageableDefault(size=9)@SortDefault.SortDefaults(
                                                          { @SortDefault(sort="authChk" , direction = Sort.Direction.ASC),
                                                            @SortDefault(sort="createdAt" , direction = Sort.Direction.ASC)
                                                          }
                                                  ) Pageable pageable) {
        var userResponses = admAuthService.userList(searchKeyWord,pageable).map(UserResponse::from);
        return Response.success(userResponses);
    }

    /**
     * 관리자 인증
     * @param userId
     * @return
     */
    @PutMapping("/{userId}/accept")
    @ApiOperation(value = "관리자 인증", notes = "관리자를 인증 한다.")
    public Response<Boolean> acceptAdmin(@PathVariable Long userId)
    {
        admAuthService.acceptAdmin(userId);

        return Response.success(true);
    }

    /**
     * 관리자 미 인증 로직
     * @param userId
     * @return
     */
    @PutMapping("/{userId}/re-accept")
    @ApiOperation(value = "관리자 미 인증", notes = "관리자를 미 인증 한다.")
    public Response<Boolean> reAcceptAdmin(@PathVariable Long userId)
    {
        admAuthService.reAcceptAdmin(userId);

        return Response.success(true);
    }
}
