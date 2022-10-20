package com.board.boardsite.controller.common;


import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.common.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/adm/excel")
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelService excelService;

    @GetMapping("/excel")
    public void downloadExcel(@RequestParam(required = false) String excelType ,
                              @RequestParam(required = false) String purchYn ,
                              @RequestParam(required = false) Long Sn ,
                              HttpServletResponse res ,
                                @AuthenticationPrincipal TripUserPrincipal tripuserPrincipal)throws IOException {
        excelService.downloadExcel(excelType,purchYn,Sn,res,tripuserPrincipal);



    }



}
