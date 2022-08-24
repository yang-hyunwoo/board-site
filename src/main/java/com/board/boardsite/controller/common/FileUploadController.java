package com.board.boardsite.controller.common;


import com.board.boardsite.dto.common.AttachFileDto;
import com.board.boardsite.service.common.FileUploadService;
import com.board.boardsite.service.common.SequenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/common")
@RequiredArgsConstructor
public class FileUploadController {

    @Value("${custom.path.upload-images}")
    private String path;

    private final FileUploadService fileUploadService;

    private final SequenceService sequenceService;

    @PostMapping("/file-upload")
    public List<AttachFileDto> fileUpload(@RequestParam("multiFile") List<MultipartFile> multipartFileList) throws IOException {
        //오늘날짜 폴더 없으면 생성
        Long fileId = sequenceService.getSeq();
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String datefolder = sdf.format(nowDate).toString();
        File dir = new File(path, datefolder);
        List<AttachFileDto> fileList = new ArrayList<>();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        int count = 0;
        for (MultipartFile multipartFile : multipartFileList) {
            String originalType;
            String contentType = multipartFile.getContentType();
            //확장자명이 존재 하지 않을 경우
            if (ObjectUtils.isEmpty(contentType)) {
                break;
            } else {
                if (contentType.contains("image/jpeg")) {
                    originalType = ".jpg";
                    count++;
                } else if (contentType.contains("image/png")) {
                    originalType = ".png";
                    count++;
                } else {
                    break;
                }
                String new_file_name = UUID.randomUUID() + originalType;
                var attachFileDto = AttachFileDto.of(fileId,
                        count,
                        multipartFile.getOriginalFilename(),
                        new_file_name,
                        dir + File.separator + new_file_name,
                        multipartFile.getSize()
                );

                fileList.add(attachFileDto);
                multipartFile.transferTo(new File(dir + File.separator + new_file_name));
            }
        }
        fileUploadService.saveImage(fileList);
        return fileList;
    }

    //TODO : 에디터 제외
    @GetMapping("/image/{fileId}")
    public UrlResource fileUpload(@PathVariable Long fileId) throws IOException {
        var filePath  = fileUploadService.findFilePath(fileId);
        return new UrlResource("file:"+filePath);
       }

   //에디터 이미지 불러오기
    @GetMapping("/image/editor/{fileId}")
    public UrlResource EditorRead(@PathVariable Long fileId) throws IOException {
        var filePath  = fileUploadService.findFilePath(fileId);
        return new UrlResource("file:"+filePath);
    }

}




