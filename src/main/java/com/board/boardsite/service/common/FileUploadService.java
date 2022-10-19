package com.board.boardsite.service.common;

import com.board.boardsite.domain.common.AttachFileId;
import com.board.boardsite.dto.common.AttachFileDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.common.FileUploadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final FileUploadRepository fileUploadRepository;


    @Transactional
    public void saveImage(List<AttachFileDto> attachFileDtos) {
        var saveImageList= attachFileDtos.stream().map(AttachFileDto::toEntity).collect(Collectors.toList());
        fileUploadRepository.saveAll(saveImageList);
    }

    @Transactional(readOnly = true)
    public String findFilePath(Long fileId,int fileChildId) {
        var findfilePath = fileUploadRepository.findById(new AttachFileId(fileId,fileChildId)).orElseThrow(()->new BoardSiteException(ErrorCode.FILE_NOT_FOUND));
        return findfilePath.getFilePath();
    }

    @Transactional(readOnly = true)
    public String findThumbFilePath(Long fileId,int fileChildId) {
        var findfilePath = fileUploadRepository.findById(new AttachFileId(fileId,fileChildId)).orElseThrow(()->new BoardSiteException(ErrorCode.FILE_NOT_FOUND));
        return findfilePath.getThumbFilePath();
    }
}
