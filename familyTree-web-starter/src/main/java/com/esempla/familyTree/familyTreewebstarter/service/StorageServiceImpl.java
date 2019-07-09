package com.esempla.familyTree.familyTreewebstarter.service;

import com.esempla.familyTree.familyTreedata.repository.FileUploadRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class StorageServiceImpl implements StorageService {
    private final FileUploadRepository fileUploadRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public void init() {

    }

    private final GridFsTemplate gridFsTemplate;

    private final GridFsOperations operations;

    @Override
    public String store(MultipartFile multipartFile) throws IOException {
        // create a "photo" namespace
        DBObject metaData = new BasicDBObject();
        metaData.put("user", "alex");
        metaData.put("contentType",multipartFile.getContentType());
        metaData.put("fileName",multipartFile.getOriginalFilename());
        InputStream inputStream = multipartFile.getInputStream();

        return gridFsTemplate.store(inputStream, multipartFile.getOriginalFilename(), multipartFile.getContentType(), metaData).toString();

    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public GridFsResource load(String id) {

        return getOneById(id);
    }

    @Override
    public void loadByName(String fileName)throws Exception{
        GridFSFile gridFsFile = gridFsTemplate.findOne(new Query(Criteria.where("filename").is(fileName)));
        GridFsResource resource = gridFsTemplate.getResource(gridFsFile.getFilename());
        inputStreamToFile(resource);

    }

    @Override
    public Resource loadAsResource(String filename) {
        GridFsResource[] gridFsResource = gridFsTemplate.getResources(filename + "*");
        GridFSFile gridFsFile = gridFsTemplate.findOne(new Query(Criteria.where("filename").is(filename)));

        return null;
    }

    @Override
    public void deleteAll() {
//                GridFSFile gridFsFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
//                gridFsTemplate.find(new Query(Criteria.where("_id").is(id)));
//        gridFsTemplate.delete(new Query(Criteria.where("_id").is(id)));
    }

    public GridFsResource getOneById(String id) {
        GridFSFile gridFsFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        GridFsResource resource = gridFsTemplate.getResource(gridFsFile.getFilename());

//        GridFSFile file = operations.findOne(new Query(Criteria.where("_id").is(id)));
//        GridFsResource resource = operations.getResource(file.getFilename());
//        inputStreamToFile(resource.);
        return resource;
    }

    private void inputStreamToFile(GridFsResource resource) {
        try {
            InputStream is = resource.getInputStream();

            OutputStream os = new FileOutputStream("./" + resource.getFilename());

            byte[] buffer = new byte[1024];
            int bytesRead;
            //read from is to buffer
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            is.close();
            //flush OutputStream to write any buffered data to file
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
