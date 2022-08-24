package com.board.boardsite.dto.common;

import com.board.boardsite.domain.common.AttachFile;

public record AttachFileDto(
        Long fileId,
        int fileChildId,
        String originFileName,

        String fileName,
        String filePath,
        Long fileSize,
        boolean deleted
) {

    public static AttachFileDto of(
                         Long fileId,
                         int fileChildId,
                         String originFileName,
                         String fileName,
                         String filePath,
                         Long fileSize)
    {
        return new AttachFileDto(fileId,
                fileChildId,
                originFileName,
                fileName,
                filePath,
                fileSize,
                false
        );
    }



    public static AttachFileDto from(AttachFile entity) {
        return new AttachFileDto(
                entity.getFileId(),
                entity.getFileChildId(),
                entity.getOriginFileName(),
                entity.getFileName(),
                entity.getFilePath(),
                entity.getFileSize(),
                entity.isDeleted()
        );
    }

    public AttachFile toEntity() {
        return AttachFile.of(
                fileId,
                fileChildId,
                originFileName,
                fileName,
                filePath,
                fileSize,
                deleted
        );
    }
}
