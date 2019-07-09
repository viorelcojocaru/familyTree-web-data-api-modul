package com.esempla.familyTree.familyTreewebstarter.web;

import com.esempla.familyTree.familyTreedata.domain.CountryDto;
import com.esempla.familyTree.familyTreewebstarter.service.StorageService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bson.BSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class FileUploadController {

    private final StorageService storageService;


    public ModelAndView listUploadedFiles(ModelAndView model) throws IOException {
        model.setViewName("/uploadForm");
        model.addObject("myFormObject", new MyFormObject());

        return model;
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping(value = "/uploadForm", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String postHandleFileUpload(@ModelAttribute ExportDestination exportDestination, ModelMap modelMap) throws IOException {
        modelMap.addAttribute("exportDestination", exportDestination);
        if (exportDestination.getFile().isEmpty()) {
            throw new IllegalArgumentException("Empty upload");
        }
        storageService.store(exportDestination.getFile());
        System.out.println(exportDestination);
        System.out.println(exportDestination.getFile());
//        BSONObject bsonObject=exportDestination;
//        storageService.store(bsonObject)
        return "/uploadForm";
    }

    @GetMapping({"/uploadForm", "/upload"})
    public ModelAndView handleFileUpload() {

        ModelAndView model = new ModelAndView();
        model.addObject("myFormObject", new MyFormObject());


        model.setViewName("/uploadForm");
        return model;
    }

    @GetMapping("/searchForm")
    public ModelAndView postHandleSearchFileForm(@ModelAttribute("myFormObject") MyFormObject myFormObject,
                                                 BindingResult result) throws Exception {
        if (myFormObject.getId() != null && !myFormObject.getId().isEmpty())
            storageService.load(myFormObject.getId());
        if (myFormObject.getName() != null && !myFormObject.getName().isEmpty())
            storageService.loadByName(myFormObject.getName());

        return handleFileUpload();
    }


    @Data
    public class MyFormObject {
        String id;
        String name;
    }

}
