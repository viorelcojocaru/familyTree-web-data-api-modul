package com.esempla.familyTree.familyTreedata.repository;

import com.esempla.familyTree.familyTreedata.domain.FileUpload;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileUploadRepository extends MongoRepository<FileUpload , String> {
}
