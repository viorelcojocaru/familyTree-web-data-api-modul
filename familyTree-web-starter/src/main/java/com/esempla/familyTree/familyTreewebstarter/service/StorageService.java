package com.esempla.familyTree.familyTreewebstarter.service;

import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    void init();

    String store(MultipartFile file) throws IOException;

    Stream<Path> loadAll();

    GridFsResource load(String id);
    void loadByName(String fileName) throws Exception;

    Resource loadAsResource(String filename);

    void deleteAll();

}
