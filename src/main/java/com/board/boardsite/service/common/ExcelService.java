package com.board.boardsite.service.common;

import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.dto.travel.TravelAgencyDto;
import com.board.boardsite.dto.travel.TravelAgencyListDto;
import com.board.boardsite.repository.adm.travel.AdmTravelAgencyListRepository;
import com.board.boardsite.repository.adm.travel.AdmTravelAgencyRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExcelService {
    private final AdmTravelAgencyRepository admTravelAgencyRepository;

    private final AdmTravelAgencyListRepository admTravelAgencyListRepository;


    /* TODO: 엑셀 다운을 공통 함수 추출 하기 */
    @Transactional
    public void downloadExcel(String excelType , HttpServletResponse res , TripUserPrincipal tripuserPrincipal) throws IOException {
        if(excelType.equals("TRAVELAGENCY")) {
            List<TravelAgencyDto> list = admTravelAgencyRepository.findAll().stream().map(TravelAgencyDto::from).collect(Collectors.toList());

            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("여행사");
            int rowNo = 0 ;

            CellStyle headStyle = workbook.createCellStyle();
            headStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.AQUA.getIndex());
            headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font font = workbook.createFont();
            font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
            font.setFontHeightInPoints((short) 13);
            headStyle.setFont(font);


            Row headerRow = sheet.createRow(rowNo++);
            headerRow.createCell(0).setCellValue("번호");
            headerRow.createCell(1).setCellValue("여행사");
            headerRow.createCell(2).setCellValue("주소");
            headerRow.createCell(3).setCellValue("전화번호");
            headerRow.createCell(4).setCellValue("사용유무");

            for(int i=0; i<=4; i++){
                    headerRow.getCell(i).setCellStyle(headStyle);
                }
            for(TravelAgencyDto travelAgencyDto:list) {
                Row row = sheet.createRow(rowNo++);
                row.createCell(0).setCellValue((rowNo-1));
                row.createCell(1).setCellValue(travelAgencyDto.name());
                row.createCell(2).setCellValue(travelAgencyDto.address());
                row.createCell(3).setCellValue(travelAgencyDto.tel());
                String used = "";
                if(travelAgencyDto.deleted()) {
                    used = "미 사용";
                } else {
                    used = "사용";
                }
                row.createCell(4).setCellValue(used);
            }
                sheet.setColumnWidth(0, 2000);
                sheet.setColumnWidth(1, 3000);
                sheet.setColumnWidth(2, 10000);
                sheet.setColumnWidth(3, 6000);
                res.setContentType("ms-vnd/excel");
                res.setHeader("Content-Disposition", "attachment;filename=boardlist.xls");

                workbook.write(res.getOutputStream());
                workbook.close();
        } else if(excelType.equals("TRAVELAGENCYLIST")) {
            List<TravelAgencyListDto> list;
            if(tripuserPrincipal.role().equals("SUPER")) {
                list = admTravelAgencyListRepository.findAll().stream().map(TravelAgencyListDto::from).collect(Collectors.toList());
            } else {
                list = admTravelAgencyListRepository.findByTravelAgency_Id(tripuserPrincipal.travelAgencyId()).stream().map(TravelAgencyListDto::from).collect(Collectors.toList());

            }

            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("여행사");
            int rowNo = 0 ;

            CellStyle headStyle = workbook.createCellStyle();
            headStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.AQUA.getIndex());
            headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font font = workbook.createFont();
            font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
            font.setFontHeightInPoints((short) 13);
            headStyle.setFont(font);


            Row headerRow = sheet.createRow(rowNo++);
            headerRow.createCell(0).setCellValue("번호");
            headerRow.createCell(1).setCellValue("여행사");
            headerRow.createCell(2).setCellValue("제목");
            headerRow.createCell(3).setCellValue("도시");
            headerRow.createCell(4).setCellValue("가격");
            headerRow.createCell(5).setCellValue("할인율");
            headerRow.createCell(6).setCellValue("할인가격");
            headerRow.createCell(7).setCellValue("등록일");
            headerRow.createCell(8).setCellValue("종료일");
            headerRow.createCell(9).setCellValue("출발일");
            headerRow.createCell(10).setCellValue("종료여부");

            for(int i=0; i<=10; i++){
                headerRow.getCell(i).setCellStyle(headStyle);
            }
            for(TravelAgencyListDto travelAgencyListDto:list) {
                Row row = sheet.createRow(rowNo++);
                row.createCell(0).setCellValue((rowNo-1));
                row.createCell(1).setCellValue(travelAgencyListDto.travelAgency().getName());
                row.createCell(2).setCellValue(travelAgencyListDto.title());
                row.createCell(3).setCellValue(travelAgencyListDto.city());
                row.createCell(4).setCellValue(travelAgencyListDto.real_paid());
                row.createCell(5).setCellValue(travelAgencyListDto.sale_percent());
                row.createCell(6).setCellValue(travelAgencyListDto.sale_paid());
                row.createCell(7).setCellValue(travelAgencyListDto.travel_start_at());
                row.createCell(8).setCellValue(travelAgencyListDto.travel_end_at());
                row.createCell(9).setCellValue(travelAgencyListDto.travelRealTripAt());
                String used = "";
                if(travelAgencyListDto.deleted()) {
                    used = "종료";
                } else {
                    used = "진행 중";
                }
                row.createCell(10).setCellValue(used);
            }
            res.setContentType("ms-vnd/excel");
            res.setHeader("Content-Disposition", "attachment;filename=boardlist.xls");

            workbook.write(res.getOutputStream());
            workbook.close();
        }
    }


}
