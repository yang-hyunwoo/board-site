package com.board.boardsite.domain.common;

import com.board.boardsite.domain.AuditingFields;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "fileChildId")
})
@IdClass(AttachFileId.class)
@Entity
public class AttachFile extends AuditingFields {

    @Id
    private Long fileId;

    @Id
    @Setter
    private int fileChildId;

    @Setter
    @Column(nullable = false , length=300)
    private String originFileName;

    @Setter
    @Column(nullable = false , length=300)
    private String fileName;

    @Setter
    @Column(nullable = false , length=300)
    private String filePath;

    @Setter
    @Column(nullable = false , length=300)
    private String thumbFilePath;

    @Setter
    @Column(nullable = false)
    private Long fileSize;

    @Setter
    @Column(nullable = false)
    private boolean deleted;

    protected AttachFile() {}

    private AttachFile(Long fileId,
                       int fileChildId,
                       String originFileName,
                       String fileName ,
                       String filePath,
                       String thumbFilePath,
                       Long fileSize,
                       boolean deleted)
    {
        this.fileId      = fileId;
        this.fileChildId = fileChildId;
        this.originFileName = originFileName;
        this.fileName = fileName;
        this.filePath = filePath;
        this.thumbFilePath = thumbFilePath;
        this.fileSize =fileSize;
        this.deleted = deleted;
    }

    public static AttachFile of(Long fileId,
                                int fileChildId,
                                String originFileName,
                                String fileName ,
                                String filePath,
                                String thumbFilePath,
                                Long fileSize,
                                boolean deleted)
    {
        return new AttachFile(fileId,
                fileChildId,
                originFileName,
                fileName,
                filePath,
                thumbFilePath,
                fileSize,
                deleted);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttachFile that)) return false;
        return fileId != null && fileId.equals(that.getFileId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileId);
    }

}
