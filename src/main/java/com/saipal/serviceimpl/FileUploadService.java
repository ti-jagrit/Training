package com.saipal.serviceimpl;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@Service
public class FileUploadService {
	public static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads";

        public boolean uploadImage(MultipartFile image, String username) {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String uploadPath = uploadDir.getAbsolutePath() +"/"+username;
        File uploadFile = new File(uploadPath);

        try {
            image.transferTo(uploadFile);
            return true;
        } catch (Exception e) {
            System.out.println("Error:"+e.getMessage());
            return false;
        }        
    }
}



