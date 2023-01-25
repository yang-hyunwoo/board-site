package com.board.boardsite.controller.common;


import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.common.ExcelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(tags ={"엑셀 추출 관련 Controller"})
@RestController
@RequestMapping("/api/adm/excel")
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelService excelService;

    @GetMapping("/excel")
    @ApiOperation(value = "엑셀 추출", notes = "엑셀을 추출 한다.")
    public void downloadExcel(@RequestParam(required = false) String excelType ,
                              @RequestParam(required = false) String purchYn ,
                              @RequestParam(required = false) Long Sn ,
                              HttpServletResponse res ,
                                @AuthenticationPrincipal TripUserPrincipal tripuserPrincipal)throws IOException {
        excelService.downloadExcel(excelType,purchYn,Sn,res,tripuserPrincipal);



    }



}
