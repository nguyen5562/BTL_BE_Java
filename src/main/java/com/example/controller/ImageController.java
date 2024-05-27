package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/")
public class ImageController {
    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Không có tệp nào được tải lên.", HttpStatus.BAD_REQUEST);
        }

        try {
            // Tạo tên tệp duy nhất
            String originalFilename = file.getOriginalFilename();
            String extension = "";
            int index = originalFilename.lastIndexOf('.');
            if (index > 0) {
                extension = originalFilename.substring(index);
            }

            // Tạo tên tệp theo định dạng yêu cầu
            String uniqueFileName = "file-" + System.currentTimeMillis() + extension;

            // Tạo thư mục lưu trữ nếu chưa tồn tại
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Lưu tệp
            Path filePath = Paths.get(uploadDir, uniqueFileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return new ResponseEntity<>(new UploadResponse(uniqueFileName), HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    static class UploadResponse {
        private String path;

        public UploadResponse(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
