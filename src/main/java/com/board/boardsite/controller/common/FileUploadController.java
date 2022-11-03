package com.board.boardsite.controller.common;


import com.board.boardsite.dto.common.AttachFileDto;
import com.board.boardsite.service.common.FileUploadService;
import com.board.boardsite.service.common.SequenceService;
import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/common")
@RequiredArgsConstructor
public class FileUploadController {

    @Value("${custom.path.upload-images}")
    private String path;

    @Value("${custom.path.thumbnail-images}")
    private String thumbnailPath;

    @Value("${cloudinary.cloud_name}")
    private String cloud_name;

    @Value("${cloudinary.api_key}")
    private String api_key;

    @Value("${cloudinary.api_secret}")
    private String api_secret;

    private final FileUploadService fileUploadService;

    private final SequenceService sequenceService;


    @PostMapping("/file-upload")
    public List<AttachFileDto> fileUpload(@RequestParam("multiFile") List<MultipartFile> multipartFileList) throws IOException {
        Map config = new HashMap();

        config.put("cloud_name", cloud_name);
        config.put("api_key", api_key);
        config.put("api_secret", api_secret);

        Cloudinary cloudinary = new Cloudinary(config);

        //오늘날짜 폴더 없으면 생성
        Long fileId = sequenceService.getSeq();
        List<AttachFileDto> fileList = new ArrayList<>();
        int count = 0;
        for (MultipartFile multipartFile : multipartFileList) {
            String contentType = multipartFile.getContentType();
            //확장자명이 존재 하지 않을 경우
            if (ObjectUtils.isEmpty(contentType)) {
                break;
            } else {
                if (contentType.contains("image/jpeg")) {
                    count++;
                } else if (contentType.contains("image/png")) {
                    count++;
                } else {
                    break;
                }
                File file = multipartFileToFile(multipartFile);

                String filePath = cloudinary.uploader().upload(file, null).get("secure_url").toString();
                String[] fileFileName = filePath.split("/");

                var attachFileDto = AttachFileDto.of(fileId,
                        count,
                        file.getName(),
                        fileFileName[7],
                        filePath,
                        filePath,
                        multipartFile.getSize()
                );
                fileList.add(attachFileDto);
            }
        }
        fileUploadService.saveImage(fileList);
        return fileList;
    }
    public static File multipartFileToFile(MultipartFile file) throws IOException {

        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();

        return convFile;

    }

    @GetMapping("/image/{fileId}/{fileChildId}")
    public String fileRead(@PathVariable Long fileId,@PathVariable int fileChildId) throws IOException {
        var filePath  = fileUploadService.findFilePath(fileId,fileChildId);
        return filePath;
    }

    /*cloudinary 미 사용시

     */
//    @PostMapping("/file-upload")
//    public List<AttachFileDto> fileUpload(@RequestParam("multiFile") List<MultipartFile> multipartFileList) throws IOException {
//        //오늘날짜 폴더 없으면 생성
//        Long fileId = sequenceService.getSeq();
//        Date nowDate = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        String datefolder = sdf.format(nowDate).toString();
//        File dir = new File(path, datefolder);
//        File thumbDir = new File(thumbnailPath , datefolder);
//        List<AttachFileDto> fileList = new ArrayList<>();
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//        if(!thumbDir.exists()){
//            thumbDir.mkdirs();
//        }
//        int count = 0;
//        for (MultipartFile multipartFile : multipartFileList) {
//            String originalType;
//            String contentType = multipartFile.getContentType();
//            //확장자명이 존재 하지 않을 경우
//            if (ObjectUtils.isEmpty(contentType)) {
//                break;
//            } else {
//                if (contentType.contains("image/jpeg")) {
//                    originalType = ".jpg";
//                    count++;
//                } else if (contentType.contains("image/png")) {
//                    originalType = ".png";
//                    count++;
//                } else {
//                    break;
//                }
//                String new_file_name = UUID.randomUUID() + originalType;
//                String new_thumb_file_name = "thumb_"+new_file_name;
//                var attachFileDto = AttachFileDto.of(fileId,
//                        count,
//                        multipartFile.getOriginalFilename(),
//                        new_file_name,
//                        dir + File.separator + new_file_name,
//                        thumbDir + File.separator + new_thumb_file_name,
//                        multipartFile.getSize()
//                );
//                fileList.add(attachFileDto);
//                multipartFile.transferTo(new File(dir + File.separator + new_file_name));
//                File thumbnailFile = new File(thumbDir + File.separator + new_thumb_file_name);
//                Thumbnailator.createThumbnail(new File(dir + File.separator + new_file_name),thumbnailFile,200,200);
//            }
//        }
//        fileUploadService.saveImage(fileList);
//        return fileList;
//    }


//    @GetMapping("/image/{fileId}/{fileChildId}")
//    public UrlResource fileRead(@PathVariable Long fileId,@PathVariable int fileChildId) throws IOException {
//        var filePath  = fileUploadService.findFilePath(fileId,fileChildId);
//        return new UrlResource("file:"+filePath);
//    }

    @GetMapping("/image/thumb/{fileId}/{fileChildId}")
    public UrlResource thumbFileRead(@PathVariable Long fileId,@PathVariable int fileChildId) throws IOException {
        var filePath  = fileUploadService.findThumbFilePath(fileId,fileChildId);
        return new UrlResource("file:"+filePath);
    }
}




