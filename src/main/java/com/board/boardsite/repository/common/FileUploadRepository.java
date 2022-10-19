package com.board.boardsite.repository.common;

import com.board.boardsite.domain.common.AttachFile;
import com.board.boardsite.domain.common.AttachFileId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadRepository extends JpaRepository<AttachFile, AttachFileId> {
}
