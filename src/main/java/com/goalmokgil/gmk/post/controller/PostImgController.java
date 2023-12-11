package com.goalmokgil.gmk.post.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostImgController {
    private final String rootPath = System.getProperty("user.dir");
    private final String postImgPath = rootPath + "/src/main/resources/static/postImg/";
    private final String staticImgPath = "/static/postImg/";

    @PostMapping("/uploadImg")
    public ResponseEntity<String> uploadImg(@RequestParam("postImg") MultipartFile img) {
        if (img.isEmpty()) {
            return null;
        }

        String originalFileName = img.getOriginalFilename();
        String storeFileName = UUID.randomUUID() + "." + extractExt(originalFileName);
        String realFilePath = postImgPath + storeFileName;
        log.info("upload request img path: {}", realFilePath);
        try {
            img.transferTo(new File(realFilePath));
            return ResponseEntity.ok(staticImgPath + storeFileName);
        } catch (IOException e) {
            throw new RuntimeException("사진 저장에 실패했습니다.");
        }
    }
   @PostMapping("/uploadImgList")
    public ResponseEntity<List<String>> uploadImgList(@RequestParam("postImgList") List<MultipartFile> imgFiles) {
        if (imgFiles.isEmpty()) {
            return null;
        }
        ArrayList<String> fileNames = new ArrayList<>();

        for (MultipartFile img : imgFiles) {
            if (img.getSize() == 0) {
                throw new RuntimeException("사진 저장에 실패했습니다.");
            }
            String originalFileName = img.getOriginalFilename();
            String storeFileName = UUID.randomUUID() + "." + extractExt(originalFileName);
            log.info("store file name: {}", storeFileName);
            String realFilePath = postImgPath + storeFileName;
            log.info("upload request img path: {}", realFilePath);
            try {
                img.transferTo(new File(realFilePath));
                fileNames.add(staticImgPath+storeFileName);
            } catch (IOException e) {
                throw new RuntimeException("사진 저장에 실패했습니다.");
            }
        }
        return ResponseEntity.ok(fileNames);

    }
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }



}
