package com.board.boardsite.domain.common;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AttachFileId implements Serializable {
    private Long fileId;

    private int fileChildId;

public AttachFileId() {}

public AttachFileId(Long fileId,int fileChildId) {
    this.fileId = fileId;
    this.fileChildId = fileChildId;
}

}
