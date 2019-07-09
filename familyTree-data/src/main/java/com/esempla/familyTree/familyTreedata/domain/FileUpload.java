package com.esempla.familyTree.familyTreedata.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
public class FileUpload {
    @Id
    private String id;

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(String filePath) {
        File f = new File(filePath);
        this.file = (MultipartFile) f;
    }
}
